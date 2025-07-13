        const API_KEY = "AIzaSyB0vKyodEZr64x1BR0HYA9m892ZUmvoD0c";
        const API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";

        // Importación del módulo solo cuando sea necesario
        async function cargarGoogleGenerativeAI() {
            const { GoogleGenerativeAI } = await import("https://esm.run/@google/generative-ai");
            const genAI = new GoogleGenerativeAI(API_KEY);
            const model = genAI.getGenerativeModel({ model: "gemini-pro" });
            const messageBox = document.querySelector(".message_container");

            const temaEspecifico = "Actua como un chat de deportes, solo responde preguntas relacionado a eso\n";

            document.querySelector(".chatbox__send--footer").addEventListener("click", async () => {
                const consulta = document.querySelector(".input").value.trim();

                if (consulta.length > 0) {
                    desactivarBoton();

                    let message = `
                        <div class="messages__item messages__item--operator">
                            ${consulta}
                        </div>
                    `;

                    messageBox.insertAdjacentHTML("beforeend", message);
                    document.querySelector(".input").value = '';

                    let carga = `
                        <div class="messages__item messages__item--typing">
                            <span class="messages__dot"></span>
                            <span class="messages__dot"></span>
                            <span class="messages__dot"></span>
                        </div>
                    `;

                    messageBox.insertAdjacentHTML("beforeend", carga);

                    try {
                        const result = await model.generateContent(`${temaEspecifico}\n${consulta}`);
                        const response = await result.response;
                        const text = response.text();

                        let typingElement = document.querySelector('.messages__item--typing');
                        if (typingElement) {
                            typingElement.remove();
                        }

                        let respuesta = `
                            <div class="messages__item messages__item--visitor">
                                ${text}
                            </div>
                        `;

                        messageBox.insertAdjacentHTML("beforeend", respuesta);
                    } catch (error) {
                        let typingElement = document.querySelector('.messages__item--typing');
                        if (typingElement) {
                            typingElement.remove();
                        }

                        let respuesta = `
                            <div class="messages__item messages__item--visitor">
                                Problemas en la consulta
                            </div>
                        `;
                        messageBox.insertAdjacentHTML("beforeend", respuesta);
                    }

                    activarBoton();
                }
            });

            function desactivarBoton() {
                const botonConsulta = document.querySelector(".chatbox__send--footer");
                botonConsulta.disabled = true;
                botonConsulta.innerText = "Enviando...";
            }

            function activarBoton() {
                const botonConsulta = document.querySelector(".chatbox__send--footer");
                botonConsulta.disabled = false;
                botonConsulta.innerText = "Enviar";
            }
        }

        // Llamar a la función para cargar el módulo
        cargarGoogleGenerativeAI();
