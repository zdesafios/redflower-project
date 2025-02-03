
# Redflower

Redflower foi concebido com o ituito de disponibilizar uma ferramenta "no code" opensource.

## Tecnologias utilizadas
* Java
* Maven
* Ecosistema do Spring Boot
* Mongodb

## Componentes importantes

#### Step

*Step* representa uma etapa do processo, até o momento temos 2 tipos conceituais de Step, os que aceitas customizações em um nível granular utilizando Operations, bem como existe o step predifinido, com toda a sua lógica especifica para ser executada.

#### StepSchema
*StepSchema* é uma classe base que representa a definição(em json) de um step, é com ela que conseguimos parametrizar ou customizar nossos steps.

#### Operation
*Operation* é a menor representação de uma execução no redflower, não é possível compor uma operação com outras.
Exemplos:
* criar variavel
* somar
* dividir
* mutiplicar
* subtrair
* etc

#### OperationSchema
*OperationSchema* é uma classe base que representa a definição(em json) de um operation, é com ela que conseguimos parametrizar ou customizar nossos operations.

#### Context
*Context* é no contexto onde criamos e usamos variáveis globais, qua podem ser utilizadas em todos os steps da pipeline.

## Componentes builtin

#### GenericStep

*GenericStep* é um step que dependende da adição de operações, pois sem as operações o step nao tem o que executar.

```java
@Component("genericStep")
@RequiredArgsConstructor
public class GenericStep extends DefaultStep<GenericStepSchema> {
	
    // Responsavel por buscar os beans no container de dependencias
	private final BeanFactory beanFactory;
	
	@Override
	public void onStart(Context context, GenericStepSchema schema) {
        // Obtemos todas as operações dinifinida no schema GenericStepSchema
		List<OperationSchema> operationsSchemas = schema.getOperations();
		operationsSchemas.stream().forEach(op-> {
            // Para cada operação pegamos a instancia do container
			Operation operation = beanFactory.getBean(op.getOperation(), Operation.class);
			if(null == operation) {
				throw new RuntimeException("Operação %s não definida".formatted(op.getOperation()));
			}
            //Se a instancia nao for nula, então executamos a operação
			operation.run(GenericStep.this, op, context);
		});
	}

}
```

Abaixo temos a sua definição em json
```json
{
    "type":"genericStep",
    "name":"criarvar",
    "next":"mostrarvar",
    "operations":[
    
        {
            "operation":"variableAssignment",
            "scope":"GLOBAL",
            "name":"valor1_global",
            "source_type":"JSON_DATA",
            "expression":"$.valor1"
        },
        {
            "operation":"variableAssignment",
            "scope":"GLOBAL",
            "name":"valor2_global",
            "source_type":"JSON_DATA",
            "expression":"$.valor2"
        }
    ]
},
```



#### PipelineResultStep
Esse step nao aceita adicição de operações, mas apenas parametrizações(nomes das variáveis que serão expostas).

```java
@Component("pipelineResultStep")
public class PipelineResultStep extends DefaultStep<PipelineResultSchema> {

	@Override
	public void onStart(Context context, PipelineResultSchema schema) {
		
		Map<String, Object> result = new HashMap<>();
		
        // Obtemos todas as variaveis que estão no contexto e adicionamos em um map
		schema.getVars().stream().forEach(name-> result.put(name, context.getVar(name)));
		

        //Adicionamos esse map no result da pipeline, em alqgum momento(quando finalizar a pipeline) essa variavel vai ser utilizada para retornar ao usuário.
		context.createVar("pipeline-result", result);
	}

}
```
Exemplo do schema
```json
{
    "type":"pipelineResultStep",
    "name":"mostrarvar",
    "next":".end",
    "vars":["valor1_global", "valor2_global", "valor1xvalor2", "valor1+valor2"]
}
```

#### VariableAssignmentOperation

Operação responsavel por criar variaveis, no contexto global(acessivel por todas as steps) ou local(acessivel apenas no step current)

```java
@Component("variableAssignment")
public class VariableAssignmentOperation extends DefaultOperation {
	

	@Override
	public void onRun(Step current, OperationSchema operationSchema, Context context) {
		VariableAssignmentOperationSchema schema = operationSchema.impl();
		
		if(schema.getScope().isGlobal()) {
			context.createVar(schema.getName(), getValue(current, schema, context));
		} else {
			current.createVar(schema.getName(), getValue(current, schema, context));
		}
	}
	
	// TODO Criar um componente pra acessar as variaveis locais, globais e json_data
	private Object getValue(Step current, VariableAssignmentOperationSchema schema, Context context) {
		Object value = null;
		if(schema.getSourceType().isJsonData()) {
			value = getFromJsonData(schema, context);
		} else if(schema.getSourceType().isLiteral()) {
			value = schema.getValue();
		} else if(schema.getSourceType().isVar()) {
			value = getFromVar(current, schema, context, schema.getName()); 
		}
		
		return value;
	}
	
	private Object getFromJsonData(VariableAssignmentOperationSchema schema, Context context) {
		if(!schema.getScope().isGlobal()) {
			throw new RuntimeException("JSON DATA exists only in GLOBAL scope");
		}
		return context.getVarFromJsonData(schema.getExpression());
	}
	
	private Object getFromVar(Step current, VariableAssignmentOperationSchema schema, Context context, String name) {
		Object value = null;
		if(schema.getScope().isGlobal()) {
			value = context.getVar(name);
		} else {
			value = current.getVar(name);
		}
		
		return value;
	}

}
```

Exemplo do schema
```json
{
    "operation":"variableAssignment",
    "scope":"GLOBAL",
    "name":"valor1_global",
    "source_type":"JSON_DATA",
    "expression":"$.valor1"
},
```

#### Operações de adição, subtração, mutiplicação e divisão
```
NumberSubtractOperation
NumberMultiplyOperation
NumberDivideOperation
NumberAddOperation
```

Será mostrado apenas 1 operação, pois, todas tem a estruturas iguais.

```java
@Component("numberSum")
@RequiredArgsConstructor
public final class NumberAddOperation extends DefaultOperation {
	// Componente reutilizado para todas as oeprações(+, -, /, *), esse delegate recebe um objeto CalulatorStrategy
	private final NumberCalculateOperationDalegate calculateDelegate;

	@Override
	public void onRun(Step stepCurrent, OperationSchema operationSchema, Context context) {
		// Chama a operaçãodelegate e recebe o valor do calculo
        Number result = calculateDelegate.onRun(stepCurrent, operationSchema, context, new SumCalculatorStrategy());
		
		NumberCalculatorOperationSchema schema = operationSchema.impl();
		PutVarResultSchema resultSchema = schema.getResult();

        // guarda o resultado local ou global, a depender do schema definido
		multiplyResult(stepCurrent, resultSchema, result, context);
	}
	
	private void multiplyResult(Step stepCurrent, PutVarResultSchema resultSchema, Number result, Context context) {
		if(resultSchema.getScope().isGlobal()) {
			context.createVar(resultSchema.getName(), result);
		} else {
			stepCurrent.createVar(resultSchema.getName(), result);
		}
	}
}
```

Estrategia para realizar o calculo
```java
 class SumCalculatorStrategy implements CalulatorStrategy {

	@Override
	public Number calculate(Number right, Number left) {
		if(null == right || null == left) {
			return null;
		}
		
		return right.doubleValue() + left.doubleValue();
	}
	 
 }
```

Exemplo do schema, esse equema define 2 numero(esquerda e direita) para serem calculados, bem como também define o scope e nome da váriavel resultado
```json
"operation":"numberMultiply",
"right": {
    "scope":"GLOBAL",
    "source_type":"JSON_DATA",
    "expression":"$.valor1"
},
"left": {
    "scope":"GLOBAL",
    "source_type":"JSON_DATA",
    "expression":"$.valor2"
},
"result":{
    "scope":"GLOBAL",
    "name":"valor1xvalor2"
}
```

## Rodar o serviço

### Mongodb

Devemos iniciar uma instancia do mongodb, que será responsavel por guardar o json de definição(schema)

#### Via docker
```docker
docker run -d --network some-network --name some-mongo \
	-e MONGO_INITDB_ROOT_USERNAME=mongo \
	-e MONGO_INITDB_ROOT_PASSWORD=mongo \
	mongo
```

### API
Depois de baixado o projeto, devemos entrar no diretório rais.

```shell
cd redflower-project
```
#### Via mvn
```shell
mvn spring-boot:run
```

Será iniciado o servidor que ouvirá na porta 8080


### Postman

redflower.postman_collection.json
Coleção do postman
```json
{
	"info": {
		"_postman_id": "aae9e950-a481-465c-8189-ed47f4bb742f",
		"name": "redflower",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "352577"
	},
	"item": [
		{
			"name": "Create Project by Json File",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/project",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"project"
					]
				}
			},
			"response": []
		},
		{
			"name": "Start",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/start/project/67a0195ba4dd7075c402479e",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"start",
						"project",
						"67a0195ba4dd7075c402479e"
					]
				}
			},
			"response": []
		}
	]
}
```

#### Endpoint pra criar a pipeline
```curl
http://localhost:8080/project
```
Deve ser passado o arquivo json com a definição

#### Endpoint pra 'startar' a pipeline
```curl
http://localhost:8080/start/project/{id}
```
O Id conseguimos na exeução de criação


### Arquivo de definição
```json
{
	"name": "calculos_varios_steps_1",
	"pipeline": {
		"type":"SYNC",
		"data_json": "{\"valor1\": 2, \"valor2\": 3,\"valor3\": 6.5,\"valor4\": \"5.0\"}",
		"start":"calculo1",
		"steps":[
			{
				"type":"genericStep",
				"name":"calculo1",
				"next":"criarvar",
				"operations":[
					{
						"operation":"numberMultiply",
						"right": {
							"scope":"GLOBAL",
							"source_type":"JSON_DATA",
							"expression":"$.valor1"
						},
						"left": {
							"scope":"GLOBAL",
							"source_type":"JSON_DATA",
							"expression":"$.valor2"
						},
						"result":{
							"scope":"GLOBAL",
							"name":"valor1xvalor2"
						}
					},
					{
						"operation":"numberSum",
						"right": {
							"scope":"GLOBAL",
							"source_type":"JSON_DATA",
							"expression":"$.valor1"
						},
						"left": {
							"scope":"GLOBAL",
							"source_type":"JSON_DATA",
							"expression":"$.valor2"
						},
						"result":{
							"scope":"GLOBAL",
							"name":"valor1+valor2"
						}
					}
				]
			},
			{
				"type":"genericStep",
				"name":"criarvar",
				"next":"mostrarvar",
				"operations":[
				
					{
						"operation":"variableAssignment",
						"scope":"GLOBAL",
						"name":"valor1_global",
						"source_type":"JSON_DATA",
						"expression":"$.valor1"
					},
					{
						"operation":"variableAssignment",
						"scope":"GLOBAL",
						"name":"valor2_global",
						"source_type":"JSON_DATA",
						"expression":"$.valor2"
					}
				]
			},
			{
				"type":"pipelineResultStep",
				"name":"mostrarvar",
				"next":".end",
				"vars":["valor1_global", "valor2_global", "valor1xvalor2", "valor1+valor2"]
			}
		]
	}
}
```