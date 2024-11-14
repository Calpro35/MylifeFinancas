//cadastro senha
document.getElementById('togglePassword').addEventListener('click', function() {
    const passwordField = document.getElementById('id-senha');
    const toggleIcon = document.getElementById('togglePassword');

    // Alterna entre "password" e "text"
    switch (passwordField.type) {
        case 'password':
            passwordField.type = 'text'; // Mostrar senha
            toggleIcon.classList.remove('bi-eye-slash');
            toggleIcon.classList.add('bi-eye'); // Ícone de olho fechado
            break;
        default:
            passwordField.type = 'password'; // Ocultar senha
            toggleIcon.classList.remove('bi-eye');
            toggleIcon.classList.add('bi-eye-slash'); // Ícone de olho aberto
            break;
    }
});
