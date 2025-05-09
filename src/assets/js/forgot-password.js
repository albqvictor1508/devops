const form = document.querySelector("form");
form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})

const init = () => {
     const emailInput = document.querySelector("#email");
     checkout(emailInput);
}

const validateInput = () => {

}

const checkout = (emailInput) => {
    const validatedEmail = validateInput(emailInput);
    if(!validatedEmail) return;
}

const resetDefaultColor = (input) => {
    input.style.borderColor = "#aca3a3";
}