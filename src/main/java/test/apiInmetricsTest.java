package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import bodyFactory.BodyFactory;
import core.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class apiInmetricsTest extends BaseTest{


	static int empregadoId;

	@Test
	public void Test_01_cadastraEmpregadoComSucesso() throws IOException, Exception {
		empregadoId = given()
				.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
				.body(BodyFactory.bodyEmpregados("CadastroSucesso").toString())
				.when()
				.post("empregado/cadastrar")
				.then()
				.statusCode(202)
				.body("nome", is("Joao da silva"))
				.body("sexo", is("m"))
				.body("cpf", is("026.260.980-00"))
				.body("cargo", is("Executivo Design Lead Consumidor"))
				.body("admissao", is("02/02/2019"))
				.body("salario", is("1.500,00"))
				.body("comissao", is("0,00"))
				.body("tipoContratacao", is("pj"))
				.extract().path("empregadoId")
				;
	}

	@Test
	public void Test_02_listarEmpregadoCadastrado() {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.pathParam("empregadoId", empregadoId)
		.when()
		.get("empregado/list/{empregadoId}")
		.then()
		.statusCode(202)
		.body("nome", is("Joao da silva"))
		.body("sexo", is("m"))
		.body("cpf", is("026.260.980-00"))
		.body("cargo", is("Executivo Design Lead Consumidor"))
		.body("admissao", is("02/02/2019"))
		.body("salario", is("1.500,00"))
		.body("comissao", is("0,00"))
		.body("tipoContratacao", is("pj"))
		;
	}

	@Test
	public void Test_03_listarEmpregadoCadastradoSemAutenticacao() {
		given()
		.pathParam("empregadoId", empregadoId)
		.when()
		.get("empregado/list/{empregadoId}")
		.then()
		.statusCode(401)
		.body("status", is(401))
		.body("error", is("Unauthorized"))
		.body("message", is("Unauthorized"))
		.body("path", is("/empregado/list/"+empregadoId))
		;
	}

	@Test
	public void Test_04_listarTodosEmpregados() {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.when()
		.get("empregado/list_all")
		.then()
		.statusCode(200)
		;
	}

	@Test
	public void Test_05_alterarEmpregadoComSucesso() throws IOException, Exception {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.body(BodyFactory.bodyEmpregados("AlteracaoSucesso").toString())
		.pathParam("empregadoId", empregadoId)
		.when()
		.put("empregado/alterar/{empregadoId}")
		.then()
		.statusCode(202)
		.body("nome", is("Guilherme Santana"))
		.body("sexo", is("m"))
		.body("cpf", is("030.754.591-19"))
		.body("cargo", is("Analista de Testes"))
		.body("admissao", is("13/07/2020"))
		.body("salario", is("11.500,00"))
		.body("comissao", is("0,00"))
		.body("tipoContratacao", is("clt"))
		;
	}

	@Test
	public void Test_06_alterarEmpregadoSemAutenticar() throws IOException, Exception {
		given()
		.body(BodyFactory.bodyEmpregados("AlteracaoSucesso").toString())
		.pathParam("empregadoId", empregadoId)
		.when()
		.put("empregado/alterar/{empregadoId}")
		.then()
		.statusCode(401)
		.body("status", is(401))
		.body("error", is("Unauthorized"))
		.body("message", is("Unauthorized"))
		.body("path", is("/empregado/alterar/"+empregadoId))
		;
	}

	@Test
	public void Test_07_listarEmpregadoAlterado() {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.pathParam("empregadoId", empregadoId)
		.when()
		.get("empregado/list/{empregadoId}")
		.then()
		.statusCode(202)
		.body("nome", is("Guilherme Santana"))
		.body("sexo", is("m"))
		.body("cpf", is("030.754.591-19"))
		.body("cargo", is("Analista de Testes"))
		.body("admissao", is("13/07/2020"))
		.body("salario", is("11.500,00"))
		.body("comissao", is("0,00"))
		.body("tipoContratacao", is("clt"))
		;
	}

	@Test
	public void Test_08_deletarEmpregado() {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.pathParam("empregadoId", empregadoId)
		.when()
		.delete("empregado/deletar/{empregadoId}")
		.then()
		.statusCode(202)
		;
	}

	@Test
	public void Test_09_deletarEmpregadoSemAutenticacao() {
		given()
		.pathParam("empregadoId", empregadoId)
		.when()
		.delete("empregado/deletar/{empregadoId}")
		.then()
		.statusCode(401)
		.body("status", is(401))
		.body("error", is("Unauthorized"))
		.body("message", is("Unauthorized"))
		.body("path", is("/empregado/deletar/"+empregadoId))
		;
	}

	@Test
	public void Test_10_cadastraEmpregadoSemAutenticacao() throws IOException, Exception {
		given()
		.body(BodyFactory.bodyEmpregados("CadastroSucesso").toString())
		.when()
		.post("empregado/cadastrar")
		.then()
		.statusCode(401)
		.body("status", is(401))
		.body("error", is("Unauthorized"))
		.body("message", is("Unauthorized"))
		.body("path", is("/empregado/cadastrar"))
		;
	}

	@Test
	public void Test_11_listarTodosEmpregadosSemAutenticacao() {
		given()
		.when()
		.get("empregado/list_all")
		.then()
		.statusCode(401)
		.body("status", is(401))
		.body("error", is("Unauthorized"))
		.body("message", is("Unauthorized"))
		.body("path", is("/empregado/list_all"))
		;
	}

	@Test
	public void Test_12_cadastraEmpregadoSemBody() {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.when()
		.post("empregado/cadastrar")
		.then()
		.statusCode(400)
		;
	}
	
	@Test
	public void Test_13_cadastraEmpregadoCamposVazios() throws IOException, Exception {
		given()
		.headers("Authorization", "Basic aW5tZXRyaWNzOmF1dG9tYWNhbw==")
		.body(BodyFactory.bodyEmpregados("CamposVazios").toString())
		.when()
		.post("empregado/cadastrar")
		.then()
		.statusCode(400)
		;
	}



}
