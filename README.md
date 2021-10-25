# loja-aplicativos-api
API de loja de aplicativo com Spring Boot

Para executar o projeto pelo maven use o comando pelo CMD ou terminal dentro da pasta raiz do projeto: mvn spring-boot:run

Faça o teste da api rest, no navegador usando o Swagger na url: http://localhost:8080/swagger-ui/index.html

Nos campos que pedem a inserção do caminho ou path para uma pasta ou arquivo, crie uma pasta e o arquivo que reprensta um executável, use como exemplo os testes aseguir:

Que tem a pasta "upload_teste" e um arquivo qualquer com a extensão .apk ficando como no exemplo: C:\Worspaces-Eclipse\workspace-sts\loja-aplicativos-api\upload_teste.apk

Se for o path para download, use o caminho apenas de uma pasta sem extensão, exemplo:
C:\Worspaces-Eclipse\workspace-sts\loja-aplicativos-api\download-teste

Para interromper a execução do projeto use as teclas: ctrl + c e depois digite S e enter.

Quando for rodar os teste do Junit no Eclipe, primeiro abra o arquivo: application.properties
que fica em: /loja-aplicativos-api/src/main/resources/application.properties

Clique com o botão direito no arquivo LojaAplicativosApiApplication.java que fica em:
/loja-aplicativos-api/src/test/java/com/lojaaplicativosapi/LojaAplicativosApiApplicationTests.java

Escolha Run As - Junit Test




