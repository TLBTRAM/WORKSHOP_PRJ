
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .footer {
        background: linear-gradient(135deg, #1A1B30, #2C2F46); 
        color: #f1f1f1;
        padding: 4rem 0;
        margin-top: 3rem;
        font-family: 'Poppins', sans-serif;
    }

    .footer-container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 1.5rem;
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 2.5rem;
    }

    .footer-section h3 {
        font-size: 1.5rem;
        margin-bottom: 1rem;
        color: #f39c12;
        position: relative;
    }

    .footer-section h3::after {
        content: '';
        display: block;
        width: 50px;
        height: 3px;
        background: #f39c12; 
        margin-top: 0.5rem;
        border-radius: 5px;
    }

    .footer-section p {
        margin-bottom: 0.8rem;
        font-size: 1rem;
        line-height: 1.6;
        color: #dcdcdc;
    }

    .footer-links {
        list-style: none;
        padding: 0;
    }

    .footer-links li {
        margin-bottom: 0.6rem;
    }

    .footer-links a {
        color: #f1f1f1;
        text-decoration: none;
        font-size: 1rem;
        transition: all 0.3s ease;
        display: flex;
        align-items: center;
    }

    .footer-links a:hover {
        color: #f39c12; 
        transform: translateX(5px);
    }

    .social-links {
        display: flex;
        gap: 1rem;
        margin-top: 1.5rem;
    }

    .social-links a {
        color: #f1f1f1;
        text-decoration: none;
        font-size: 1.8rem;
        transition: transform 0.3s ease, color 0.3s ease;
    }

    .social-links a:hover {
        color: #f39c12;
        transform: scale(1.2);
    }

    .copyright {
        text-align: center;
        padding-top: 2.5rem;
        margin-top: 3rem;
        border-top: 1px solid rgba(255, 255, 255, 0.2);
        font-size: 1rem;
        color: #bfbfbf;
    }
</style>

<footer class="footer">
    <div class="footer-container">
        <div class="footer-section">
            <h3>About us</h3>
            <p>The exam system is an important tool to help assess the capacity and preparation of each individual, and create a fair foundation for candidates to show their abilities transparently and accurately.</p>
        </div>

        <div class="footer-section">
            <h3>Connect</h3>
            <ul class="footer-links">
                <li><a href="#">Home</a></li>
                <li><a href="#">Categories</a></li>
                <li><a href="#">Exam</a></li>
                <li><a href="#">Score</a></li>
            </ul>
        </div>

        <div class="footer-section">
            <h3>Contact</h3>
            <p>Adress: 123 Street ABC, District XYZ</p>
            <p>Email: contact@shoponline.com</p>
            <p>Phone: (84) 123-456-789</p>
        </div>

        <div class="footer-section">
            <h3>Follow the exam</h3>
            <p>Update the latest news from the exam</p>
            <div class="social-links">
                <a href="#">📱</a>
                <a href="#">💬</a>
                <a href="#">📷</a>
            </div>
        </div>
    </div>

    <div class="copyright">
        <p>&copy; 2025 . All rights are reserved.</p>
    </div>
</footer>
