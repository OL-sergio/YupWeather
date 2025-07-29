# YupWeather

## Objetivos do Projeto

YupWeather é uma aplicação de meteorologia desenvolvida para fornecer aos utilizadores informações meteorológicas precisas e atualizadas. Os principais objetivos deste projeto incluem:

-   Exibir as condições meteorológicas atuais para uma localização especificada pelo utilizador.
-   Fornecer previsões horárias e diárias detalhadas.
-   Oferecer uma interface de utilizador amigável para fácil navegação e acesso à informação.
-   Permitir que os utilizadores pesquisem informações meteorológicas por cidade.

## Componentes

A aplicação consiste nos seguintes componentes principais:

-   **Interface do Utilizador (UI):** Exibe informações meteorológicas, funcionalidade de pesquisa e configurações.
-   **Obtenção de Dados:** Recupera dados meteorológicos de uma API fiável.
-   **Processamento de Dados:** Analisa e formata os dados para exibição.
-   **Serviços de Localização:** Determina a localização atual do utilizador para atualizações meteorológicas.
-   **Armazenamento:** Guarda as preferências do utilizador e os dados de localização.

## Tecnologias Utilizadas

-   **Kotlin:** Linguagem de programação principal para o desenvolvimento Android.
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" width="30" height="30" alt="Kotlin">
-   **Java:** Utilizada para funcionalidades específicas e integrações de API.
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="30" height="30" alt="Java">
-   **Gradle:** Ferramenta de automação de compilação para gerir dependências e configuração do projeto.
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-plain.svg" width="30" height="30" alt="Gradle">
-   **Android SDK:** Kit de desenvolvimento de software para criar aplicações Android.
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg" width="30" height="30" alt="Android">
-   **Retrofit:** Um cliente HTTP seguro para Android e Java.
    <img src="https://raw.githubusercontent.com/square/retrofit/master/retrofit.png" width="30" height="30" alt="Retrofit">

## Estrutura do Projeto
A estrutura do projeto está organizada da seguinte forma:
```
`app/`
└── `src/`
    └── `main/`
        ├── `AndroidManifest.xml`
        └── `java/`
            └── `exemple/`
                └── `weatherapp/`
                    └── `api/`
                        └── `java/`
                            └── `yupweather/`
                                ├── `DailyForecastActivity.java`
                                ├── `MainActivity.java`
                                ├── `SplashActivity.java`
                                ├── `adapter/`
                                │   ├── `DailyForecastAdapter.java`
                                │   └── `HourlyForecastAdapter.java`
                                ├── `database/`
                                │   ├── `api/`
                                │   │   ├── `cliente/`
                                │   │   │   ├── `APIClientConditions.java`
                                │   │   │   ├── `APIClientDaily.java`
                                │   │   │   ├── `APIClientHourly.java`
                                │   │   │   └── `APIClientMain.java`
                                │   │   └── `service/`
                                │   │       ├── `DataServiceConditions.java`
                                │   │       ├── `DataServiceDaily.java`
                                │   │       ├── `DataServiceHourly.java`
                                │   │       └── `DataServiceMain.java`
                                │   └── `local/`
                                │       └── `SharedPreferenceLocation.java`
                                ├── `helper/`
                                │   ├── `DeserializerConditions.java`
                                │   ├── `DeserializerDaily.java`
                                │   ├── `DeserializerHourly.java`
                                │   └── `DeserializerMain.java`
                                ├── `model/`
                                │   ├── `ErrorResponse.java`
                                │   ├── `forecastdaily/`
                                │   │   ├── `DailyData.java`
                                │   │   ├── `DailyResponse.java`
                                │   │   └── `FeelsLike.java`
                                │   ├── `forecastday/`
                                │   │   ├── `WeatherConditionsDay.java`
                                │   │   └── `WeatherMainDay.java`
                                │   └── `forecasthourly/`
                                │       ├── `Clouds.java`
                                │       ├── `HourlyData.java`
                                │       ├── `HourlyItem.java`
                                │       ├── `HourlyResponse.java`
                                │       ├── `Main.java`
                                │       ├── `Sys.java`
                                │       └── `Wind.java`
                                └── `utilities/`
                                    ├── `Animations.java`
                                    ├── `Constants.java`
                                    ├── `Converts.java`
                                    ├── `CustomAlerts.java`
                                    ├── `GPSTracker.java`
                                    └── `SystemUi.java`
                                    
```
## Funcionalidade da Aplicação por Classe

-   `MainActivity`: Gere o ecrã principal, incluindo a pesquisa de localização e a exibição do tempo atual.
-   `DailyForecastActivity`: Exibe a previsão do tempo para os próximos dias.
-   `SplashActivity`: Exibe o ecrã de abertura ao iniciar a aplicação.
-   `DailyForecastAdapter`: Preenche a lista de previsão diária com dados.
-   `HourlyForecastAdapter`: Preenche a lista de previsão horária com dados.
-   `APIClient...`: Clientes Retrofit para obter dados meteorológicos (atuais, diários, horários).
-   `DataService...`: Interfaces de serviço para as chamadas à API do Retrofit.
-   `SharedPreferenceLocation`: Gere o armazenamento e a recuperação das preferências de localização do utilizador.
-   `Deserializer...`: Desserializadores personalizados para analisar as respostas JSON da API.
-   `model/...`: Classes de modelo (POJO) que representam os dados da API.
-   `utilities/...`: Classes de utilitários para animações, constantes, conversões, alertas, rastreamento GPS e gestão da UI do sistema.

## Execução e Configuração

### GitHub

1.  Clone o repositório para a sua máquina local:

    ```bash
    git clone https://github.com/OL-sergio/YupWeather.git
    ```

### Android Studio

1.  Abra o projeto no Android Studio.
2.  Certifique-se de que tem o Android SDK e as ferramentas de compilação necessárias instaladas.
3.  Compile o projeto usando o Gradle.
4.  Execute a aplicação num emulador ou dispositivo físico.

### Configuração

-   **Chave da API:** Terá de obter uma chave de API do fornecedor de dados meteorológicos e adicioná-la ao projeto. Procure um ficheiro chamado `local.properties` ou similar e adicione a sua chave lá.

    ```properties
    sdk.dir=C:\\Users\\sergi\\AppData\\Local\\Android\\Sdk
    WEATHER_API_KEY="SUA_CHAVE_DE_API_AQUI"
    ```

## Bibliotecas e Frameworks

-   **AndroidX:** Um conjunto de bibliotecas utilizadas para apoiar as práticas modernas de desenvolvimento Android e fornecer retrocompatibilidade.
-   **Retrofit:** Um cliente HTTP seguro para Android e Java, que facilita o consumo de serviços web RESTful. O Retrofit serializa automaticamente a resposta JSON usando um conversor que é especificado ao criar a instância do Retrofit.

## Imagens e Ícones

Inclua imagens e ícones relevantes para melhorar o apelo visual e a compreensão do README.

-   Logótipo da aplicação
-   Capturas de ecrã da aplicação
-   Ícones para as tecnologias utilizadas
This was a project that I was working on that had a bug in the source code that i could resolve.
https://github.com/OL-sergio/YupWeather-OLD
