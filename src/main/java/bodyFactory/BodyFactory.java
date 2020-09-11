package bodyFactory;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import util.ReadProperties;

public class BodyFactory {

	public static JSONObject bodyEmpregados(String tipoMassa) throws IOException, Exception {
		JSONObject dtEmpregados = new JSONObject();
		Properties mt = new ReadProperties().read("massas");
		String[] dados = StringUtils.split((String) mt.get("post."+tipoMassa),";");
		try {

			dtEmpregados.put("admissao", dados[0].equals("null") ? "": dados[0]);
			dtEmpregados.put("cargo", dados[1].equals("null") ? "": dados[1]);
			dtEmpregados.put("comissao", dados[2].equals("null") ? "": dados[2]);
			dtEmpregados.put("cpf", dados[3].equals("null") ? "": dados[3]);
			dtEmpregados.put("departamentoId", dados[4].equals("null") ? "": dados[4]);
			dtEmpregados.put("nome", dados[5].equals("null") ? "": dados[5]);
			dtEmpregados.put("salario", dados[6].equals("null") ? "": dados[6]);
			dtEmpregados.put("sexo", dados[7].equals("null") ? "": dados[7]);
			dtEmpregados.put("tipoContratacao", dados[8].equals("null") ? "": dados[8]);

		} catch(JSONException e) {
			e.printStackTrace();
		}

		return dtEmpregados;
	}


}
