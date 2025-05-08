const form = document.querySelector("form");
form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})


const init = () => {
    const usernameInput = document.querySelector("#username");
    const passwordInput = document.querySelector("#password");
    const formData = {usernameInput, passwordInput};
    checkout(formData);
}

const validateInputs = ({usernameInput, passwordInput}) => {
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

const handleMessage = (msg, field, isError) => {
    const div = document.createElement("div");
    div.textContent = msg;
    div.classList.add("message")
    isError === true ? div.classList.add("error-message") : div.classList.add("safe-message"); 
    field.insertAdjacentElement("afterend",div);
}

const clearMessages = () => {
    const allErrorMessages = document.querySelectorAll(".message")
    for(const msg of allErrorMessages) {
        msg.remove();
    }
}

const checkout = async (formData) => {
    const validatedData = validateInputs(formData); 
    
    if(!validatedData) return; 

    console.log({
        username: validatedData.username,
        password: validatedData.password
    });
    // const data = await fetch("", {
    //     method: "POST",
    //     headers: "Content-Type", //header de json, n sei qual é 
    //     body: {
    //         username: validatedData.username
    //         password: validatedData.password
    //     }
    // })
}