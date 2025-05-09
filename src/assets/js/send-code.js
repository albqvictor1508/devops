const form = document.querySelector("form");
form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})

const init = () => {
     const emailInput = document.querySelector("#email");     
     checkout(emailInput);
}

const validateInput = (emailInput) => {
    clearMessages();
    const email = emailInput.value;
    let valid = true;

    if(!email.length) {
        handleMessage("Este campo precisa ser preenchido", emailInput, true)
        valid = false;
    }

    if(!valid) return;
    return email;
}

const checkout = (emailInput) => {
    const validatedEmail = validateInput(emailInput);
    if(!validatedEmail) return;

    //await fetch("", {});
}

const resetDefaultColor = (input) => {
    input.style.borderColor = "#aca3a3";
}

const handleMessage = (msg, field, isError) => {
    const div = document.createElement("div");
    field.insertAdjacentElement("afterend",div);
    div.textContent = msg;
    changeFieldColor(div, field);
}

const clearMessages = () => {
    const allMessages = document.querySelectorAll(".message")
    for(const msg of allMessages) {
        msg.remove();
    }
}

const changeFieldColor = (div, field) => {
    div.classList.add("message")
    field.style.borderColor = "red";
    div.classList.add("error-message");
}