// const sideLinks = document.querySelectorAll('.sidebar .side-menu li a:not(.logout)');
const sideLinks = document.querySelectorAll('.sidebar .side-menu li button:not(.logout)');
sideLinks.forEach(item => {
    const li = item.parentElement;
    item.addEventListener('click', () => {
        sideLinks.forEach(i => {
            i.parentElement.classList.remove('active');
        })
        li.classList.add('active');
    })
});

const menuBar = document.querySelector('.content nav .bx.bx-menu');
const sideBar = document.querySelector('.sidebar');

menuBar.addEventListener('click', () => {
    sideBar.classList.toggle('close');
});

const searchBtn = document.querySelector('.content nav form .form-input button');
const searchBtnIcon = document.querySelector('.content nav form .form-input button .bx');
const searchForm = document.querySelector('.content nav form');

searchBtn.addEventListener('click', function (e) {
    if (window.innerWidth < 576) {
        e.preventDefault;
        searchForm.classList.toggle('show');
        if (searchForm.classList.contains('show')) {
            searchBtnIcon.classList.replace('bx-search', 'bx-x');
        } else {
            searchBtnIcon.classList.replace('bx-x', 'bx-search');
        }
    }
});

window.addEventListener('resize', () => {
    if (window.innerWidth < 768) {
        sideBar.classList.add('close');
    } else {
        sideBar.classList.remove('close');
    }
    if (window.innerWidth > 576) {
        searchBtnIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }
});

const toggler = document.getElementById('theme-toggle');

toggler.addEventListener('change', function () {
    if (this.checked) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
});
document.getElementById("logoutForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn chặn việc gửi biểu mẫu mặc định.
});

document.getElementById("logoutButton").addEventListener("click", function () {
    var confirmLogout = confirm("Bạn có muốn đăng xuất không?");
    if (confirmLogout) {
        // Nếu người dùng xác nhận đăng xuất, gửi biểu mẫu để chuyển hướng người dùng đến trang /logout.
        document.getElementById("logoutForm").submit();
    }
});

document.getElementById("cancelButton").addEventListener("click", function () {
    var cancelLogout = confirm("Bạn có muốn hủy đăng xuất không?");
    if (cancelLogout) {
        // Xử lý khi người dùng xác nhận hủy đăng xuất, ví dụ: không có thay đổi gì.
    }
});