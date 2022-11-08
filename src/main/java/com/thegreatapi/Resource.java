package com.thegreatapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Path("/")
public class Resource {

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response hello() throws IOException {
        try (InputStream file = Resource.class.getResourceAsStream("/directory/dummy.pdf")) {
            if (file == null) {
                throw new FileNotFoundException();
            } else {
                return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                               .header("Content-Disposition", "inline; filename=\"dummy.pdf\"")
                               .build();
            }
        }
    }

    @GET
    @Path("/hello/{file_with_path}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFileWithPath(@PathParam("file_with_path") String fileWithPath) throws IOException {
        try (InputStream file = Resource.class.getResourceAsStream("/directory/" + fileWithPath)) {
            if (file == null) {
                throw new FileNotFoundException();
            } else {
                return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                               .header("Content-Disposition", "inline; filename=\"" + java.nio.file.Path.of(fileWithPath).getFileName() +  "\"")
                               .build();
            }
        }
    }
}
