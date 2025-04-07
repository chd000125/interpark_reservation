import React, { useEffect } from "react";

function BookingResultModal({ result, data, onClose }) {
    useEffect(() => {
        if (result === "success") {
            alert("예매에 성공했습니다!");
        } else {
            alert("예매에 실패했습니다!");
            onClose(); // 실패 시 모달 닫기
        }
    }, [result, onClose]);

    if (result !== "success") return null;

    return React.createElement("div", {
        style: {
            position: "fixed",
            top: 0,
            left: 0,
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0, 0, 0, 0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000
        }
    }, React.createElement("div", {
        style: {
            backgroundColor: "#fff",
            padding: "30px",
            borderRadius: "10px",
            width: "300px",
            textAlign: "center"
        }
    }, [
        React.createElement("h2", { key: "title" }, "예매 정보"),
        React.createElement("p", { key: "pTitle" }, `공연명: ${data?.pTitle}`),
        React.createElement("p", { key: "pPlace" }, `장소: ${data?.pPlace}`),
        React.createElement("button", {
            key: "closeBtn",
            onClick: onClose,
            style: {
                marginTop: "20px",
                padding: "10px 20px",
                border: "none",
                backgroundColor: "#333",
                color: "#fff",
                borderRadius: "6px",
                cursor: "pointer"
            }
        }, "닫기")
    ]));
    
}

export default BookingResultModal;
