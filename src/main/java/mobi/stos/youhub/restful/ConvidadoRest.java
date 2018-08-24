package mobi.stos.youhub.restful;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mobi.stos.youhub.bean.Convidado;
import mobi.stos.youhub.bean.Ingresso;
import mobi.stos.youhub.bean.Manager;
import mobi.stos.youhub.bo.IConvidadoBo;
import mobi.stos.youhub.bo.IIngressoBo;
import mobi.stos.youhub.bo.IManagerBo;
import mobi.stos.youhub.enumm.SituacaoConvidadoEnum;
import mobi.stos.youhub.restful.model.AssociadoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gilberto
 */
@Component
@Path("/convidado")
public class ConvidadoRest {

    @Autowired
    private IConvidadoBo convidadoBo;

    @Autowired
    private IManagerBo managerBo;

    @Autowired
    private IIngressoBo ingressoBo;

    @POST
    @Path("salvar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(Convidado convidado) {
        try {

            if (convidado != null) {
                if (convidado.getManager() != null && convidado.getManager().getId() != null) {
                    convidado.setSituacao(SituacaoConvidadoEnum.RELACIONADO);
                    this.convidadoBo.persist(convidado);
                } else {
                    convidado.setSituacao(SituacaoConvidadoEnum.NAO_RELACIONADO);
                    this.convidadoBo.persist(convidado);
                }
                return Response.status(Response.Status.OK).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Path("associarmanager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response associarmanager(AssociadoManager associadoManager) {
        try {

            if (associadoManager != null) {
                Manager manager = this.managerBo.load(associadoManager.getIdManager());
                Convidado convidado = this.convidadoBo.load(associadoManager.getIdConvidado());
                convidado.setManager(manager);
                convidado.setSituacao(SituacaoConvidadoEnum.RELACIONADO);
                this.convidadoBo.persist(convidado);
                return Response.status(Response.Status.OK).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("listar/relacionado/{idEvento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar(@PathParam("idEvento") Long idEvento) {
        try {

            List<Ingresso> ingressos = this.ingressoBo.listConvidados(idEvento, SituacaoConvidadoEnum.RELACIONADO);
            if (ingressos != null) {
                List<Convidado> convidados = new ArrayList<>();

                ingressos.forEach((ingresso) -> {
                    convidados.add(ingresso.getConvidado());
                });

                return Response.status(Response.Status.OK).entity(convidados).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("listar/noarelacionado/{idEvento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response noarelacionado(@PathParam("idEvento") Long idEvento) {
        try {

            List<Ingresso> ingressos = this.ingressoBo.listConvidados(idEvento, SituacaoConvidadoEnum.NAO_RELACIONADO);
            if (ingressos != null) {
                List<Convidado> convidados = new ArrayList<>();

                ingressos.forEach((ingresso) -> {
                    convidados.add(ingresso.getConvidado());
                });
                return Response.status(Response.Status.OK).entity(convidados).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("listar/falta/{idEvento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response falta(@PathParam("idEvento") Long idEvento) {
        try {

            List<Ingresso> ingressos = this.ingressoBo.listFalta(idEvento);
            if (ingressos != null) {
                List<Convidado> convidados = new ArrayList<>();

                ingressos.forEach((ingresso) -> {
                    convidados.add(ingresso.getConvidado());
                });
                return Response.status(Response.Status.OK).entity(convidados).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("listar/presnete/{idEvento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response presnete(@PathParam("idEvento") Long idEvento) {
        try {

            List<Ingresso> ingressos = this.ingressoBo.listPresente(idEvento);
            if (ingressos != null) {
                List<Convidado> convidados = new ArrayList<>();

                ingressos.forEach((ingresso) -> {
                    convidados.add(ingresso.getConvidado());
                });
                return Response.status(Response.Status.OK).entity(convidados).build();
            }

            return Response.status(Response.Status.FORBIDDEN).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}