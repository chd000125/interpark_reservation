// import { BrowserRouter, Routes, Route } from "react-router-dom";
// import Home from "./pages/Home";
// import SelectSeat from "./pages/SelectSeat";
// import ConfirmBooking from "./pages/ConfirmBooking";
// import CompleteBooking from "./pages/CompleteBooking";
// import "./styles/global.css";
//
// function App() {
//     return (
//         <BrowserRouter>
//             <Routes>
//                 <Route path="/" element={<Home />} />
//                 <Route path="/select/:pId" element={<SelectSeat />} />
//                 <Route path="/confirm/:pId" element={<ConfirmBooking />} />
//                 <Route path="/complete/:pId" element={<CompleteBooking />} />
//             </Routes>
//         </BrowserRouter>
//     );
// }
//
// export default App;

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import BookingPage from "./pages/BookingPage";
import PopupPage from "./pages/PopupPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<BookingPage />} />
                <Route path="/popup" element={<PopupPage />} />
            </Routes>
        </Router>
    );
}

export default App;
