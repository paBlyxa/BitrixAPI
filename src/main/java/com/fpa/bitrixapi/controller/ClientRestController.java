package com.fpa.bitrixapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fpa.bitrixapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@RestController
public class ClientRestController {

    private final static Logger logger = LoggerFactory.getLogger(ClientRestController.class);

    private final static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    @Autowired
    WebClient webClient;

    @GetMapping("/users")
    public List<User> getUsers(){
        return getAll(USER_GET, User.class, Collections.singletonMap("USER_TYPE", "employee"), null);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return getAll(PROJECTS_GET, Project.class, null, null);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        String[] select = {"TITLE", "EMAIL"};
        return getAll(CRM_COMPANY_LIST, Company.class, null, select);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return getAll(DEPARTMENT_GET, Department.class, null, null);
    }

    @GetMapping("/disks")
    public List<Disk> getDisks() {
        return getAll(DISK_STORAGE_GETLIST, Disk.class, Collections.singletonMap("ENTITY_TYPE", "group"), null);
    }

    @GetMapping("projectusers/{projectId}")
    public List<UserGroup> getProjectUsers(@PathVariable Integer projectId){
        if (projectId != null){
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>(1);
            map.put("ID", Collections.singletonList(Integer.toString(projectId)));
            return get(PROJECTS_USER_GET, UserGroup.class, map);
        } else {
            return Collections.emptyList();
        }
    }

    private <T> List<T> get(String uri, Class<T> classT, MultiValueMap<String, String> params) {
        Mono<String> result = performGet(uri, params);
        List<T> list = null;
        try {
            String value = result.block();
            logger.debug("Get from bitrix: {}", value);
            JsonNode root = mapper.readTree(value);
            ArrayNode data = (ArrayNode) root.get("result");
            list = new ArrayList<>(data.size());
            for (JsonNode node : data){
                list.add(mapper.treeToValue(node, classT));
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Mono<String> performGet(String uri, MultiValueMap<String, String> params) {
        Mono<String> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/" + uri)
                    .queryParams(params)
                    .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }

    private <T> List<T> getAll(String uri, Class<T> classT, Map<String, String> filter, String[] select){
        MultiValueMap<String, String> params = prepareParams(filter, select);
        List<T> result = new ArrayList<>();
        List<T> items = get(uri, classT, params);
        int start = 0;
        // if items more than 50, then request next pack (bitrix send packs with limit 50)
        while (items.size() == 50){
            result.addAll(items);
            start += 50;
            params.put("start", Collections.singletonList(Integer.toString(start)));
            items = get(uri, classT, params);
        }
        result.addAll(items);
        return result;
    }

    private MultiValueMap<String, String> prepareParams(Map<String, String> filter, String[] select){
        int initialSize = 1 + (filter != null ? filter.size() : 0)
                + (select != null ? select.length : 0);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(initialSize);

        params.put("order[ID]", Collections.singletonList("ASC"));
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, String> entry : filter.entrySet()) {
                params.put("filter[" + entry.getKey() + "]", Collections.singletonList(entry.getValue()));
            }
        }
        if (select != null && select.length > 0) {
            for (int i = 0; i < select.length; i++) {
                params.put("SELECT[" + i + "]", Collections.singletonList(select[i]));
            }
        }


        return params;
    }

    public final static String USER_GET = "user.get";
    public final static String DEPARTMENT_GET = "department.get";
    public final static String PROJECTS_GET = "sonet_group.get";
    public final static String PROJECTS_USER_GET = "sonet_group.user.get";
    public final static String CRM_COMPANY_LIST = "crm.company.list";

    public final static String DISK_STORAGE_GET = "disk.storage.get";
    public final static String DISK_STORAGE_GETLIST = "disk.storage.getlist";
    public final static String DISK_STORAGE_GETCHILDREN = "disk.storage.getchildren";
    public final static String DISK_STORAGE_ADDFOLDER = "disk.storage.addfolder";

    public final static String DISK_FOLDER_GET = "disk.folder.get";
    public final static String DISK_FOLDER_GETCHILDREN = "disk.folder.getchildren";
    public final static String DISK_FOLDER_UPLOADFILE = "disk.folder.uploadfile";
    public final static String DISK_FOLDER_ADDSUBFOLDER = "disk.folder.addsubfolder";

    public final static String TASK_TASK_GET = "tasks.task.get";
    public final static String TASK_TASK_ADD = "tasks.task.add";
}
