document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener("scroll", function () {
        var imagesLeft = document.querySelectorAll(".animate-img2");
        var imagesRight = document.querySelectorAll(".animate-img");
        var screenHeight = window.innerHeight;

        imagesLeft.forEach(function (img) {
            var position = img.getBoundingClientRect();
            if (position.top < screenHeight) {
                img.classList.add("active");
            }
        });

        imagesRight.forEach(function (img) {
            var position = img.getBoundingClientRect();
            if (position.top < screenHeight) {
                img.classList.add("active");
            }
        });
    });
});