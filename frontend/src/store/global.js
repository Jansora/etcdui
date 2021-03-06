import React, {createContext, useReducer} from 'react';
import {THEME} from "../constants";


export const defaultValue = {

    // 主题色
    theme: localStorage.getItem(THEME) ? localStorage.getItem(THEME) : 'violet',
    breadcrumb: [],
    title: 'HomePage',
};

export const GlobalStore = createContext(defaultValue);

const reducer = (state, action) => {

    switch(action.type) {
        case 'theme':
            return {...state, theme: action.payload};

        case 'title':
            return {...state, title: action.payload};
        default:
            return {...state, ...action.payload}
    }
}


const StoreProvider = props => {
    const [store, dispatch] = useReducer(reducer, defaultValue);
    return (
        <GlobalStore.Provider value={{...store, dispatch}}>
            {props.children}
        </GlobalStore.Provider>
    );
};
export default StoreProvider;
