<!-- title -->
<h1 align="center">
    <span>Sistema de Pedidos</span>
</h1>

<br>

<!-- badges -->
<div align="left">
    <img src="https://img.shields.io/badge/license-MIT-yellow" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/version-1.0-green" alt="badge icon"></img>
    <img src="https://img.shields.io/badge/repo size-32,5 MB KB-orange" alt="badge icon"></img>
</div>

<br>

<!-- About -->
## <img src="https://cdn2.iconfinder.com/data/icons/flat-pack-1/64/Computer-512.png" alt="todo list image icon" width="40px" align="center"> Sobre o Projeto

Este sistema foi criado para enviar (upload) e baixar (download) arquivos no serviço Amazon S3 (AWS)


O que o sistema faz:

- Upload: Salva seus arquivos com segurança na nuvem da Amazon.

- Download: Busca o arquivo na nuvem e o salva automaticamente em uma pasta dentro do container

- Tratamento de Exceções: O sistema identifica e avisa se algo estiver errado.


<hr>
<br>

## <img src="https://images.unsplash.com/vector-1753805036965-091d0993b2c5?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDE2fHx8ZW58MHx8fHx8" alt="todo list image icon" width="40px" align="center"> Gestão de Arquivos Duplicados

Os arquivos baixados são armazenados no diretório `/app/downloads do container`. Para evitar a perda de dados, o sistema não sobrescreve arquivos existentes: caso você baixe um arquivo com nome idêntico a um já salvo, o sistema o renomeia automaticamente, garantindo que todas as versões sejam mantidas em segurança.

<img src="./readme_imgs/rename_files.png" alt ="image icon" width="200px" align="center">

<hr>
<br>

<!-- Custom Exception Handler -->
## <img src="https://cdn4.iconfinder.com/data/icons/common-app-symbols-round-colored/1024/caveat_proviso_disclaimer_exception_app_round_colored-512.png" alt ="image icon" width="40px" align="center"> Exceptions customizadas

Todas as exceções foram personalizadas para um melhor entendimento do usuário

<br>

**Exemplo 1**

<img src="./readme_imgs/credentials_not_found.png" alt ="image icon" width="960px" align="center">
	
<br>

**Exemplo 2**

<img src="./readme_imgs/file_not_found.png" alt ="image icon" width="960px" align="center">

<hr>
<br>

<!-- Technologies -->
## <img src="https://cdn4.iconfinder.com/data/icons/general-office/91/General_Office_48-256.png" alt="todo list image icon" width="40px" align="center"> Tecnologias

- Java 17
- Spring Boot 4.0.2
- Spring Web
- Spring Boot DevTools
- lombok
- Docker 27.0.3
- Amazon S3 (AWS SDK)


<hr>
<br>

## <img src="https://cdn1.iconfinder.com/data/icons/internet-45/64/http-link-internet-domain-1024.png" alt ="image icon" width="40px" align="center"> Endpoints


| Método Http | URI | Descrição | Status Code esperado |                  
| :---:       | :--- |  :---    | :---:                |
| POST   | `http://localhost:8080/upload`     |         Envio de arquivo para o Bucket S3      | 200 |
| GET     | `http://localhost:8080/download/{arquivo_tal}`      |         Busca no S3 e salva na pasta do container            |  200 |

<hr>
<br>

<!-- Build and run -->
## <img src="https://cdn3.iconfinder.com/data/icons/start-up-4/44/rocket-256.png" alt="todo list image icon" width="40px" align="center"> Rodando a aplicação

### Requisitos
- [git](https://git-scm.com/downloads)
- [Docker](https://docs.docker.com/desktop/wsl/)

<br>

### Passo a passo

1. Clone esse repositório
    ```bash
    git clone https://github.com/lGabrielDev/upload_download_arquivos_S3
    ```
<br>

2. Vá ao diretorio

     ```bash
     cd upload_download_arquivos_S3
     ```

<br>

3. Configure as credenciais da AWS no seu arquivo .env

    <img alt="environment variables image example" src="./readme_imgs/credentials.png" width="350px">

<br>

4. Suba o container

     ```bash
     docker compose up --build;
     ```

<br>

5. O serviço estará disponível em: `http://localhost:8080`

<hr>
<br>

<!-- Credits -->
<h2>
    <img src="https://cdn4.iconfinder.com/data/icons/thank-you/256/Artboard_4_copy-512.png" alt="thumbs up icon image" width="40px" align="center">
    <span>Créditos</span>
</h2>

<p>As imagens usadas nesse projeto foram retiradas dos seguintes sites:</p>

- [shields.io](https://shields.io/)
- [iconfinder](https://www.iconfinder.com/)
- [storyset](https://storyset.com/)
- [vecteezy](https://www.vecteezy.com)
- [imgix](https://www.imgix.com/)

     <br>

     <span>Thanks!</span>

<hr>
<br>


<!-- License -->
## <img src="https://cdn4.iconfinder.com/data/icons/jetflat-2-multimedia-vol-3/60/0042_049_license_agreement_file_document_paper_page_sheet-512.png" alt="todo list image icon" width="40px" align="center"> Licença --> MIT

O projeto está sob a licença do [MIT](./LICENSE.txt).

<hr>
<br>

