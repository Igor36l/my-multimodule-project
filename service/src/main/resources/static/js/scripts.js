/* src/main/resources/static/js/scripts.js */
document.addEventListener('DOMContentLoaded', function() {
    const carousels = document.querySelectorAll('.carousel');
    carousels.forEach(carousel => {
        const leftButton = carousel.parentNode.querySelector('.left-button');
        const rightButton = carousel.parentNode.querySelector('.right-button');

        leftButton.addEventListener('click', () => {
            carousel.scrollBy({ left: -300, behavior: 'smooth' });
        });

        rightButton.addEventListener('click', () => {
            carousel.scrollBy({ left: 300, behavior: 'smooth' });
        });
    });
});
