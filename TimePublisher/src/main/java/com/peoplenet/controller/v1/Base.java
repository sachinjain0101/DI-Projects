package com.peoplenet.controller.v1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peoplenet.model.TimeCardParams;
import com.peoplenet.service.module.SimpleProducer;
import com.peoplenet.service.module.TimeCardOps;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Type;
import java.util.List;

@Path("/v1")
public class Base {

    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(Base.class);

    @GET
    @Path("/available")
    @Produces(MediaType.TEXT_PLAIN)
    public String available() {
        return "TimePublisher v1: available: YES";
    }

    @GET
    @Path("/timecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitSingleTimeCard(@Context UriInfo info) {

        String client = info.getQueryParameters().getFirst("client");
        String group = info.getQueryParameters().getFirst("group");
        String ssn = info.getQueryParameters().getFirst("ssn");
        String pped = info.getQueryParameters().getFirst("pped");

        TimeCardParams tcp = new TimeCardParams(client, group, ssn, pped);
        List<String> lstTC = TimeCardOps.getTimeCard(tcp);
        SimpleProducer.start(lstTC);

        String retTcList = "";

        Type listType = new TypeToken<List<String>>(){}.getType();
        Gson gson = new Gson();
        retTcList = gson.toJson(lstTC, listType);

        return Response.ok(retTcList,MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("/alltimecard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitAllTimeCard(@Context UriInfo info) {

        List<String> lstTC = TimeCardOps.getTimeCard(null);
        SimpleProducer.start(lstTC);

        String retTcList = "";

        Type listType = new TypeToken<List<String>>(){}.getType();
        Gson gson = new Gson();
        retTcList = gson.toJson(lstTC, listType);

        return Response.ok(retTcList,MediaType.APPLICATION_JSON_TYPE).build();
    }
}


/*
ObjectMapper mapper = new ObjectMapper();
try {
    retTcList = mapper.writeValueAsString(lstTC);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
*/