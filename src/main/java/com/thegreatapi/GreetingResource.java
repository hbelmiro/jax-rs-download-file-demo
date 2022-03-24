package com.thegreatapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Path("/hello")
public class GreetingResource {

    private static final String FILE_NAME = "/dummy.pdf";

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response hello() throws IOException {
        try (InputStream file = GreetingResource.class.getResourceAsStream(FILE_NAME)) {
            if (file == null) {
                throw new FileNotFoundException();
            } else {
                return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                               .header("Content-Disposition", "inline; filename=\"dummy.pdf\"")
                               .build();
            }
        }
    }
}
