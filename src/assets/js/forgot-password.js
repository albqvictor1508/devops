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
    const email = emailInput.value;
}

const checkout = async (emailInput) => {
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
    changeFieldColor(div, field, isError);
}

const clearMessages = () => {
    const allErrorMessages = document.querySelectorAll(".message")
    for(const msg of allErrorMessages) {
        msg.remove();
    }
}

const changeFieldColor = (div, field, isError) => {
    div.classList.add("message")
    field.style.borderColor = "red";
    div.classList.add("error-message");
}