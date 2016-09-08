package edu.val.cice;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerListaRecados
 */
@WebServlet("/ObtenerListaRecados")
public class ObtenerListaRecados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private List<Recado> lista_recados;
	
	private String lista_recados_json;
	
	private static long MEDIA_HORA_EN_MS = 30 * 1000 * 60;
	
	private List<Recado> getListaRecados ()
	{
		
		Recado recado1 = null;
		Recado recado2 = null;
		Recado recado3 = null;
		Recado recado4 = null;
		List<Recado> l_recado_aux = null;
		Date fecha_actual = null;
		
			
			fecha_actual = new Date ();
			Date newDate = new Date(fecha_actual.getTime() + (MEDIA_HORA_EN_MS));
			Date newDate1 = new Date(newDate.getTime() + (MEDIA_HORA_EN_MS));
			Date newDate2 = new Date(newDate1.getTime() + (MEDIA_HORA_EN_MS));
			Date newDate3 = new Date(newDate2.getTime() + (MEDIA_HORA_EN_MS));
			Date newDate4 = new Date(newDate3.getTime() + (MEDIA_HORA_EN_MS));
			Date newDate5 = new Date(newDate4.getTime() + (MEDIA_HORA_EN_MS));
			
			recado1 = new Recado (fecha_actual, "Juanito", "644121140", "Calle Manuela Karmena, 5", "Ministerio Sanidad", "LLevar los resultados de los análisis", newDate);
			recado2 = new Recado (newDate1, "Pepita", "659885025", "Calle La Concordia, 5", "Servicio de empleo", "Ve a sellar el paro a mi hijo", newDate2);
			recado3 = new Recado (newDate3, "Domingo", "666897485", "Mercado Maravillas", "Calle Londres, 5", "Traeme lo de la casqueria que encargue", newDate4);
			recado4 = new Recado (newDate4, "Anguita", "659887433", "Calle del Buenismo", "Registro Propiedad 2", "Obtener nota simple del registro de la propiedad", newDate5);
		
			l_recado_aux = new LinkedList<Recado>();
			
			l_recado_aux.add(recado1);
			l_recado_aux.add(recado2);
			l_recado_aux.add(recado3);
			l_recado_aux.add(recado4);
	
		return l_recado_aux;
	}
	
	private String getJsonListaRecados (List<Recado> lista_recados)
	{
		String str_json = null;
			
			//TODO crear string json a partir de la lista buscar ejemplo usando GSON
			Gson gson = new Gson();
			str_json = gson.toJson(lista_recados);
			
		return str_json;
	}
	
	
	@Override
	public void init() throws ServletException {
		super.init();

		//Obtengo la lista de recados
		this.lista_recados = getListaRecados();
		//Serializo la lista a Json	
		this.lista_recados_json = getJsonListaRecados(this.lista_recados);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerListaRecados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = null;
		
			this.lista_recados = getListaRecados();
			//Serializo la lista a Json	
			Collections.shuffle(lista_recados);
			
			this.lista_recados_json = getJsonListaRecados(this.lista_recados);
		
			response.setContentType("application/json");//seteo la respuesta
			response.setStatus(HttpURLConnection.HTTP_OK);//seteo el código http de que ha ido bien la cosa! OK = 200
			
			
			
			System.out.println("Puntuaciones como JSON = " + lista_recados_json);
			
			pw = response.getWriter();//obtengo acceso al body
			pw.print(this.lista_recados_json);//y escribo la puntación
		
	}

}
