import React, { useEffect } from "react";

function PopupPage() {
    const query = new URLSearchParams(window.location.search);
    const result = query.get("result");
    const pTitle = query.get("pTitle");
    const pPlace = query.get("pPlace");

    useEffect(() => {
        if (result === "success") {
            alert("예매에 성공했습니다!");
        } else {
            alert("예매에 실패했습니다!");
            window.close(); // 실패 시 창 닫기
        }
    }, [result]);

    if (result !== "success") return null;

    return React.createElement("div", {
        style: {
            padding: "40px",
            fontFamily: "sans-serif",
            textAlign: "center"
        }
    }, [
        React.createElement("h2", { key: "title" }, "예매 정보"),
        React.createElement("p", { key: "pTitle" }, `공연명: ${pTitle}`),
        React.createElement("p", { key: "pPlace" }, `장소: ${pPlace}`),
        React.createElement("button", {
            key: "closeBtn",
            onClick: () => window.close(),
            style: {
                marginTop: "20px",
                padding: "10px 20px",
                border: "none",
                backgroundColor: "#333",
                color: "#fff",
                borderRadius: "6px",
                cursor: "pointer"
            }
        }, "창 닫기")
    ]);
}

export default PopupPage;
