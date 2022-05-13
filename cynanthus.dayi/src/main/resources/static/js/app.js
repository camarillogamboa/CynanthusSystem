function resize() {
    console.log("cambiando tamaño")
    // Get elements and necessary element heights
    var contentDiv = document.getElementById("work-area");
    var headerDiv = document.getElementById("head-bar");
    var headerHeight = headerDiv.offsetHeight;

    // Get view height
    var viewportHeight = document.getElementById("main").clientHeight;

    // Compute the content height - we want to fill the whole remaining area
    // in browser window
    contentDiv.style.minHeight = viewportHeight - headerHeight;
}

window.onload = resize;
window.onresize = resize;