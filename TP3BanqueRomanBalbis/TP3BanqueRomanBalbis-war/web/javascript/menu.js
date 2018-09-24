
var navItem = document.getElementsByClassName('nav-item');
console.log('coucou');
for (var i = 0; i < navItem.length; i++) {
    navItem[i].classList.remove("active");
    if (window.location.pathname.includes(navItem[i].getAttribute('name'))) {
        navItem[i].classList.add("active");
    }
}

if (!window.location.pathname.includes('faces')) {
    navItem[0].classList.add("active");
}
