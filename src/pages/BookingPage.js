import React from "react";

function BookingPage() {
    const handleBookingClick = () => {
        // 예시: 성공 결과 전달
        const result = "success"; // 또는 "fail"
        const pTitle = encodeURIComponent("레미제라블");
        const pPlace = encodeURIComponent("예술의전당");

        const url = `/popup?result=${result}&pTitle=${pTitle}&pPlace=${pPlace}`;
        window.open(url, "BookingResultPopup", "width=800,height=600");
    };

    return (
        <div style={{ padding: "40px", textAlign: "center", fontFamily: "sans-serif" }}>
            <h2>공연 예매</h2>
            <button
                onClick={handleBookingClick}
                style={{
                    padding: "12px 24px",
                    fontSize: "16px",
                    backgroundColor: "#4CAF50",
                    color: "white",
                    border: "none",
                    borderRadius: "8px",
                    cursor: "pointer"
                }}
            >
                예매하기
            </button>
        </div>
    );
}

export default BookingPage;
