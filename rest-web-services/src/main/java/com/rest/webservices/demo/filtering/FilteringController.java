package com.rest.webservices.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservices.demo.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

// To filter the response for user
@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        return fieldsToFilter(mapping, new String[]{"field1", "field2"});
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListSomeBean() {
        List<SomeBean> someBeans =  Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        return fieldsToFilter(mapping, new String[]{"field2", "field3"});
    }

    // Dynamic filtering, we want to send selected fields into response
    private MappingJacksonValue fieldsToFilter(MappingJacksonValue mapping, String[] fields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
                filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
