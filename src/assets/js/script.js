const form = document.querySelector("form");
form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})


const init = () => {
    const usernameInput = document.querySelector("#username");
    const passwordInput = document.querySelector("#password");
    const formData = {usernameInput, passwordInput};
    validateInputs(formData);
}

const validateInputs = ({usernameInput, passwordInput}) => {
    const username = usernameInput.value;
    const password = passwordInput.value;

    if(username.length < 6) {
        handleMessage("mto pequeno esse nome ai viado", usernameInput, true);
    }

    if(password.length < 6) {
        handleMessage("mto pequeno essa senha ai viado", passwordInput, true);
    }
}

const handleMessage = (msg, field, isError) => {
    const div = document.createElement("div");
    div.textContent = msg;
    div.classList.add("message")
    isError === true ? div.classList.add("error-message") : div.classList.add("safe-message"); 
    field.insertAdjacentElement("afterend",div);
}

const clearMessages = () => {
    const allErrorMessages = document.querySelectorAll("message")
    for(const msg of allErrorMessages) {
        msg.remove();
    }
}