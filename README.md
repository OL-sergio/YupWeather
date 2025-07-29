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
    <img width="70" height="30" alt="image" src="https://github.com/user-attachments/assets/8ef23620-c72c-4cc0-a4f7-cdd83390d48b" />
-   **Android SDK:** Kit de desenvolvimento de software para criar aplicações Android.
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg" width="30" height="30" alt="Android">
-   **Retrofit:** Um cliente HTTP seguro para Android e Java.
    <img width="60" height="30" alt="image" src="https://github.com/user-attachments/assets/ccf18712-d0e7-4325-808a-8adb8dc90bec" />


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


- MainActivity: Gerencia a tela principal, incluindo busca de localização e exibição do clima atual.
- DailyForecastActivity: Exibe a previsão do tempo para os próximos dias.
- SplashActivity: Exibe a tela de splash ao iniciar o aplicativo.
- DailyForecastAdapter: Preenche a lista de previsão diária com os dados.- 
- HourlyForecastAdapter: Preenche a lista de previsão horária com os dados.
- APIClientConditions: Cliente Retrofit para obter as condições climáticas atuais.
- APIClientDaily: Cliente Retrofit para obter dados de previsão diária.
- APIClientHourly: Cliente Retrofit para obter dados de previsão horária.
- APIClientMain: Cliente Retrofit para obter dados principais do clima.
- DataServiceConditions: Interface de serviço para chamadas de API de condições climáticas atuais.
- DataServiceDaily: Interface de serviço para chamadas de API de previsão diária.
- DataServiceHourly: Interface de serviço para chamadas de API de previsão horária.
- DataServiceMain: Interface de serviço para chamadas de API de dados principais do clima.
- SharedPreferenceLocation: Gerencia o armazenamento e recuperação das preferências de localização do usuário.
- DeserializerConditions: Desserializador personalizado para dados de condições climáticas atuais.
- DeserializerDaily: Desserializador personalizado para dados de previsão diária.
- DeserializerHourly: Desserializador personalizado para dados de previsão horária.
- DeserializerMain: Desserializador personalizado para dados principais do clima.
- ErrorResponse: Classe de modelo para tratamento de respostas de erro da API.
- DailyData: Classe de modelo para dados climáticos diários.
- DailyResponse: Classe de modelo para resposta da API de previsão diária.
- FeelsLike: Classe de modelo para dados de temperatura como sensação térmica.
- WeatherConditionsDay: Classe de modelo para condições climáticas de um dia específico.
- WeatherMainDay: Classe de modelo para dados principais do clima de um dia específico.
- Clouds: Classe de modelo para dados de nuvens.
- HourlyData: Classe de modelo para dados climáticos horários.
- HourlyItem: Classe de modelo para itens de previsão horária.
- HourlyResponse: Classe de modelo para resposta da API de previsão horária.
- Main: Classe de modelo para parâmetros principais do clima (temperatura, umidade, etc.).
- Sys: Classe de modelo para dados de sistema (nascer e pôr do sol).
- Wind: Classe de modelo para dados de vento.
- Animations: Classe utilitária para tratamento de animações.
- Constants: Classe utilitária para armazenamento de valores constantes.
- Converts: Classe utilitária para operações de conversão de dados.
- CustomAlerts: Classe utilitária para exibição de alertas personalizados.
- GPSTracker: Classe utilitária para rastreamento de localização via GPS.
- SystemUi: Classe utilitária para gerenciamento de elementos da interface do sistema.

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

## Old Project
-  This was a project that I was working on that had a bug in the source code that I could resolve.
https://github.com/OL-sergio/YupWeather-OLD
