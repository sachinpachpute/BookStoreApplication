import { useEffect, useRef } from "react";
import { AUTHORIZATION_SERVER_BASE_URL, CLIENT_ID, REDIRECT_URI } from '../constants/appConstants';
import { useSearchParams, useNavigate, useLocation } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { generateCodeChallenge, generateCodeVerifier } from '../components/Pkce';
import { login } from '../actions/userActions';
import { Buffer } from "buffer";
import axios from 'axios';
import qs from 'qs';

const RedirectScreen = () => {
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();
    // let location = useLocation()
    // const redirectTo = location.search ? location.search.split('=')[1] : '/'
    // alert("will redirect to "+redirectTo);
    
    //alert('codeVerifier in redirect screen : '+sessionStorage.getItem('codeVerifier'));
    if (sessionStorage.getItem('codeVerifier') === null){
        const verifier = generateCodeVerifier();
        sessionStorage.setItem('codeVerifier', verifier);
        const codeChallenge = generateCodeChallenge();
        console.log(verifier);
        sessionStorage.setItem('codeChallenge', codeChallenge);
    } 
    
    const dispatch = useDispatch();

    const lockUseEffect = useRef(true);

    useEffect(() => {
        if(searchParams?.get('code')){    
            if (lockUseEffect.current) {
                lockUseEffect.current = false;
                const code = searchParams?.get('code');
                console.log(code);
                dispatch(login(code));
                //navigate(`${redirectTo}`);
                navigate('/');
            }
        }
    }, []);
    useEffect(() => {
        if(!searchParams?.get('code')){
            const codeChallenge = sessionStorage.getItem('codeChallenge');
            //alert('codeChallenge while requesting authorize: '+codeChallenge);
            const link = `${AUTHORIZATION_SERVER_BASE_URL}/oauth2/authorize?response_type=code&client_id=${CLIENT_ID}&scope=openid&redirect_uri=${REDIRECT_URI}&code_challenge=${codeChallenge}&code_challenge_method=S256`;            
            //const link = 'http://auth-server:3001/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256';
          
            window.location.href = link;
        }
    }, []);
}

export default RedirectScreen;