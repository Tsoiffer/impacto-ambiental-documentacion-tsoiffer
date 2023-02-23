package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.repositorios.RepositorioAreas;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AreasOrganizacionController {
  RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
  RepositorioAreas repositorioAreas = new RepositorioAreas();

  public ModelAndView mostrarPropias(Request request, Response response) {
    Organizacion unaOrganizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<Area> areas = unaOrganizacion.getAreas();

    return new ModelAndView(new HashMap<String, Object>(){{
      put("areas", areas);
    }}, "usuarioOrganizacion/areasOrganizacion.hbs");
  }
  
  public Response guardar(Request request, Response response) {
    String nombre = request.queryParams("name");
    Organizacion unaOrganizacion = obtenerOrganizacionSegunIDUsuario(request);
    unaOrganizacion.getAreas();
    //Area areaNueva = unaOrganizacion.darAltaArea(nombre);
    Area areaNueva = new Area(nombre, unaOrganizacion);

    repositorioAreas.agregar(areaNueva);

    System.out.println("antes de agregar Areas " +areaNueva.nombre);
    //repositorioOrganizaciones.actualizar(unaOrganizacion);
    System.out.println("Area agregada");

    response.redirect("/organizacion/areas");
    return response;
  }

  public Response borrar(Request request, Response response) {
    //TODO pendiente de implementación
    return response;
  }


  private Organizacion obtenerOrganizacionSegunIDUsuario(Request request) {
    return repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
  }

}
