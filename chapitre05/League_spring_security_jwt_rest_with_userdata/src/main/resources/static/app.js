document.addEventListener('DOMContentLoaded', () => {

const registerForm = document.getElementById('registerForm');
const loginForm = document.getElementById('loginForm');
const logoutButton = document.getElementById('logout');
const saisonList = document.getElementById('saisonList');

    if (registerForm) {

        registerForm.addEventListener("submit", async (e) => {
                e.preventDefault();
                const username = e.target.username.value;
                const password = e.target.password.value;

                const payload = {
                    username: username,
                    password: password,
                    roles: [
                        {
                            name: 'ADMIN'
                        }
                    ]
                }

                const response = await fetch("/user", {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                })

                if (response.ok) {
                    window.location.href = "login.html";
                } else {
                    console.log("Echec de l'inscription");
                }
            })
    }

    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
                    e.preventDefault();
                    const username = e.target.username.value;
                    const password = e.target.password.value;

                    const payload = {
                        username: username,
                        password: password
                    }

                    const response = await fetch("/login", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(payload)
                    })

                    const data = await response.json();

                    if (response.ok) {
                        localStorage.setItem('token', data.token);
                        window.location.href = "index.html";
                    } else {
                        console.log("Echec de la connexion");
                    }
                })

    }

    if (logoutButton) {
        logoutButton.addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('token')
            window.location.href = "login.html"
        })
    }

    if (saisonList) {
        const fetchSaisons = async () => {
            const token = localStorage.getItem('token');
            if (!token) {
                window.location.href = "login.html";
            }

            const response = await fetch('/saisons', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                const saisons = await response.json();
                saisons.forEach(saison => {
                    const li = document.createElement("li");
                    li.textContent = saison.libelle;
                    saisonList.appendChild(li);
                })
            } else {
                console.log("Echec lors de la récupération des saisons.")
            }

        }

        fetchSaisons();
    }

})