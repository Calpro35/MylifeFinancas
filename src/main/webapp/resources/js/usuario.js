//tela de usuario perfil
document.getElementById('toggleUser').addEventListener('click', function() {
    const passwordField = document.getElementById('id-senha');
    const toggleIcon = document.getElementById('toggleUser');

    // Alterna entre "password" e "text"
    if (passwordField.type === 'password') {
        passwordField.type = 'text'; // Mostrar senha
        toggleIcon.classList.remove('bi-eye-slash');
        toggleIcon.classList.add('bi-eye'); // Ícone de olho fechado
    } else {
        passwordField.type = 'password'; // Ocultar senha
        toggleIcon.classList.remove('bi-eye');
        toggleIcon.classList.add('bi-eye-slash'); // Ícone de olho aberto
    }
});
