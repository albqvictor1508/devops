const form = document.querySelector("form");
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