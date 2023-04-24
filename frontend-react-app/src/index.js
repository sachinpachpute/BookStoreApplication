import React from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App';
import store from './store';
import './bootstrap.min.css';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import { persistStore } from 'redux-persist';

const container = document.getElementById('root')
const root = createRoot(container)
let persistor = persistStore(store);

root.render(
    <Provider store={store}>
        <React.StrictMode> 
            <PersistGate persistor={persistor}>
                <App />
            </PersistGate>
        </React.StrictMode>
    </Provider>,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
