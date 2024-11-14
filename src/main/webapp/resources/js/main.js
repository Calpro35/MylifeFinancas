


// Função para alternar a visibilidade de todos os elementos de saldo
function toggleVisibility() {
    const saldos = document.querySelectorAll(".saldo");
    const toggleIcon = document.getElementById("toggleIcon");
    const toggleText = document.getElementById("toggleText");

    // Alterna a visibilidade de todos os elementos de saldo
    saldos.forEach(saldo => {
        if (saldo.style.display === "none") {
            saldo.style.display = "table-cell"; // Mostra todos os saldos
            toggleIcon.classList.replace("bi-eye-slash", "bi-eye"); // Ícone para "ocultar"
            toggleText.textContent = "Ocultar valores"; // Atualiza o texto
        } else {
            saldo.style.display = "none"; // Esconde todos os saldos
            toggleIcon.classList.replace("bi-eye", "bi-eye-slash"); // Ícone para "mostrar"
            toggleText.textContent = "Mostrar valores"; // Atualiza o texto
        }
    });
}



