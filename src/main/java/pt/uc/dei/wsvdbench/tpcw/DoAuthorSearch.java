package pt.uc.dei.wsvdbench.tpcw;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import pt.uc.dei.wsvdbench.tpcw.object.Book;
import pt.uc.dei.wsvdbench.tpcw.object.CartAux;
import pt.uc.dei.wsvdbench.tpcw.versions.CreateShoppingCart_Vx0;
import pt.uc.dei.wsvdbench.tpcw.versions.DoAuthorSearch_Vx0;
import pt.uc.dei.wsvdbench.tpcw.versions.DoAuthorSearch_VxA;
import pt.uc.dei.wsvdbench.util.Logging;


@Path("DoAuthorSearch")
public class DoAuthorSearch {

    @GET
    @Path("doAuthorSearch_VxA")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Obtain all books where author has name (a_lname in database) equals to String search_key",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books in json with author name equals to the search_key",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Book.class))
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "No books found for author with name equals to search_key"),
                    @ApiResponse(responseCode = "500", description = "Something really bad must have happened in our server")
            }
    )
    public Response doAuthorSearch_VxA(@Parameter(required = true) @QueryParam("search_key") String search_key) {

        List<Book> books = new DoAuthorSearch_VxA().doAuthorSearch(search_key);

        Type listType = new TypeToken<List<Book>>() {}.getType();
        List<Book> target = new LinkedList<Book>();

        for(Book b : books)
            target.add(b);


        Gson gson = new Gson();
        String json = gson.toJson(target, listType);

        if(target.size() == 0){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(json)
                    .build();
        }
        else{
            return Response.status(Response.Status.OK)
                    .entity(json)
                    .build();
        }

    }

    @GET
    @Path("doAuthorSearch_Vx0")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Obtain all books where author has name (a_lname in database) equals to String search_key",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of books in json with author name equals to the search_key",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Book.class))
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "No books found for author with name equals to search_key"),
                    @ApiResponse(responseCode = "500", description = "Something really bad must have happened in our server")
            }
    )
    public Response doAuthorSearch_Vx0(@Parameter(required = true) @QueryParam("search_key") String search_key) {

        List<Book> books = new DoAuthorSearch_Vx0().doAuthorSearch(search_key);

        Type listType = new TypeToken<List<Book>>() {}.getType();
        List<Book> target = new LinkedList<Book>();

        for(Book b : books)
            target.add(b);


        Gson gson = new Gson();
        String json = gson.toJson(target, listType);


        if(target.size() == 0){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(json)
                    .build();
        }
        else{
            return Response.status(Response.Status.OK)
                    .entity(json)
                    .build();
        }
    }
}
