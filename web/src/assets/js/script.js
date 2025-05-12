const form = document.querySelector("form");
form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})


const init = () => {
    const usernameInput = document.querySelector("#username");
    const passwordInput = document.querySelector("#password");
    const formData = {usernameInput, passwordInput};
    checkout(formData);const clearMessages = () => {
    const allErrorMessages = document.querySelectorAll(".message")
    for(const msg of allErrorMessages) {
        msg.remove();
    }
}
}

const validateInputs = ({usernameInput, passwordInput}) => {
    resetDefaultColor(usernameInput, passwordInput);
    clearMessages();

    let valid = true;
    const username = usernameInput.value;
    const password = passwordInput.value;

    if(username.length < 6) {
        handleMessage("mto pequeno esse nome ai viado", usernameInput, true);
        valid = false;
    }

    if(password.length < 6) {
        handleMessage("mto pequeno essa senha ai viado", passwordInput, true);
        valid = false
    }

    if(!valid) return;
    return {username, password};
}

const changeFieldColor = (div, field, isError) => {
    div.classList.add("message")
    field.style.borderColor = "red";
    div.classList.add("error-message");
}

const handleMessage = (msg, field, isError) => {
    const div = document.createElement("div");
    field.insertAdjacentElement("afterend",div);
    div.textContent = msg;
    changeFieldColor(div, field, isError);
}

const clearMessages = () => {
    const allErrorMessages = document.querySelectorAll(".message")
    for(const msg of allErrorMessages) {
        msg.remove();
    }
}

const checkout = async (formData) => {
    const validatedData = validateInputs(formData); 
    const rememberMe = document.querySelector("#remember-me-check")
    console.log(rememberMe.checked);
    
    if(!validatedData) return; 

    console.log({
        username: validatedData.username,
        password: validatedData.password
    });
    // const data = await fetch("http://localhost:8080/auth/register", {
    //     method: "POST",
    //     headers: "Content-Type", //header de json, n sei qual é 
    //     body: {
    //         username: validatedData.username
    //         password: validatedData.password
    //         rememberMe: validatedData.rememberMe
    //     }
    // })
}

const resetDefaultColor = (...inputs) => {
    inputs.forEach(i => {
        i.style.borderColor = "#aca3a3";
    })
}