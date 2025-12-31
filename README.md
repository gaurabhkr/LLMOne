# LLMOne 🤖

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.9-green?style=for-the-badge&logo=spring-boot)
![Spring AI](https://img.shields.io/badge/Spring_AI-1.1.2-blue?style=for-the-badge&logo=spring)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge&logo=docker)

**LLMOne** is a powerful Spring Boot application that unifies access to multiple Large Language Models (LLMs) under a single, easy-to-use interface. It leverages **Spring AI** to connect with top-tier models from Google, OpenAI, Nvidia, and DeepSeek.

## ✨ Features

*   **Unified API**: A single REST API to interact with multiple LLM providers.
*   **Multi-Model Support**: Switch seamlessly between:
    *   **MistralAI Devstral** (`mistralai/devstral-2512:free`)
    *   **DeepSeek** (`tngtech/deepseek-r1t2-chimera:free`)
    *   **Nvidia Nemotron** (`nvidia/nemotron-3-nano-30b-a3b:free`)
    *   **GPT** (`openai/gpt-oss-120b`)
*   **Modern Chat UI**: Includes a beautiful, responsive web interface built with React and Tailwind CSS.
    *   Dark/Light mode support.
    *   Real-time streaming-like experience.
    *   Syntax highlighting for code.
*   **Dockerized**: Ready for containerized deployment.

## 🚀 Getting Started

### Prerequisites

*   **Java 21** or higher
*   **Maven**
*   **Docker** (optional, for containerization)

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/LLMOne.git
    cd LLMOne
    ```

2.  **Configure Environment Variables**
    This project uses **OpenRouter** to access multiple LLMs. You need to set up your OpenRouter API key and Base URL. You can do this in `src/main/resources/application.properties` or, preferably, as environment variables.

    ```properties
    SPRING_AI_OPENAI_API_KEY=your_openrouter_api_key
    SPRING_AI_OPENAI_BASE_URL=https://openrouter.ai/api/
    ```

3.  **Build the Project**
    ```bash
    ./mvnw clean install
    ```

4.  **Run the Application**
    ```bash
    ./mvnw spring-boot:run
    ```

    The application will start on `http://localhost:8080`.

## 🖥️ Usage

### Web Interface
Open your browser and navigate to `http://localhost:8080` to use the chat interface.

### API Endpoints

You can also interact directly with the API:

| Model | Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **MistralAI** | `GET` | `/api/mistralai/{message}` | Chat with MistralAI |
| **DeepSeek** | `GET` | `/api/deepseek/{message}` | Chat with DeepSeek |
| **Nvidia** | `GET` | `/api/nvidia/{message}` | Chat with Nvidia Nemotron |
| **GPT** | `GET` | `/api/gpt/{message}` | Chat with GPT |

**Example:**
```bash
curl http://localhost:8080/api/mistralai/Hello
```



## 🛠️ Technologies Used

*   **Spring Boot 3.5.9** - Backend Framework
*   **Spring AI 1.1.2** - AI Integration
*   **React & Tailwind CSS** - Frontend UI
*   **Docker** - Containerization
*   **Maven** - Dependency Management

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1.  Fork the project
2.  Create your feature branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request
