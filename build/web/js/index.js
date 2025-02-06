/**
 * Function that resizes the screen
 * 
 * @returns {undefined}
 */
function resizeScreen() {
    $("body").css("padding-top", parseInt($("nav.navbar.fixed-top").css("height")) + 10);
}

$(window).resize(function () {
    resizeScreen();
});

$("nav.navbar.fixed-top").on('shown.bs.collapse', function () {
    resizeScreen();
});

$("nav.navbar.fixed-top").on('hidden.bs.collapse', function () {
    resizeScreen();
});

resizeScreen();
// Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })
})()