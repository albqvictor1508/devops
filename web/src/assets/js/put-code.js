const form = document.querySelector("form");

document.addEventListener("DOMContentLoaded", e => {
    const userEmail = localStorage.getItem("userEmail");
})

form.addEventListener("submit", e => {
    e.preventDefault();
    init();
})

const init = async () => {
    const codeInput = document.querySelector("#code-input");
    await fetchCode();
}

const fetchCode = async () => {
    const user = await fetch("", {
        method: "POST", 
        body: {
            email: 
        } 
    }); 
}