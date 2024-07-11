const showPopups = document.querySelectorAll('.show-popup');
    const popupContainers = document.querySelectorAll('.popup-container');

    showPopups.forEach(function(showPopup, index) {
        showPopup.addEventListener('click', function() {
            popupContainers[index].classList.add('active');
        });

        const closeBtns = popupContainers[index].querySelectorAll('.close-btn');
        closeBtns.forEach(function(closeBtn) {
            closeBtn.addEventListener('click', function() {
                popupContainers[index].classList.remove('active');
            });
        });
    });
    
    const showImages = document.querySelectorAll('.show-image');
    const BillContainers = document.querySelectorAll('.bill-container');

    showImages.forEach(function(showImage, index) {
        showImage.addEventListener('click', function() {
            BillContainers[index].classList.add('active');
        });

        const closeBtnsImages = BillContainers[index].querySelectorAll('.close-btn-image');
        closeBtnsImages.forEach(function(closeBtnsImage) {
            closeBtnsImage.addEventListener('click', function() {
                BillContainers[index].classList.remove('active');
            });
        });
    });