// package com.bongladesch.plessme.users.controller.jaxrs;

// import javax.annotation.security.RolesAllowed;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.Response;
// import javax.ws.rs.core.MediaType;

// import org.jboss.resteasy.annotations.cache.NoCache;

// import io.quarkus.security.identity.SecurityIdentity;

// @Path("/secure")
// public class SecureUserAPI {

//     private SecurityIdentity identity;

//     @GET
//     @RolesAllowed("user")
//     @Produces(MediaType.APPLICATION_JSON)
//     @NoCache
//     public Response hello() {
//         //Principal principal = identity.getPrincipal();
//         // if(principal instanceof KeycloakPrincipal) {
//         //     logger.info("Before cast");
//         //     KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) identity.getPrincipal();
//         //     logger.info("Keycloak");
//         // } else {
//         //     logger.info("Not Keycloak");
//         // }
//         //String idToken = principal.getKeycloakSecurityContext().getIdTokenString();
//         //logger.info("Keycloak ID: " + idToken);
//         return Response.ok("{\"Hello\":\"" + identity.getPrincipal().getName() + "\"}").build();
//     }
// }