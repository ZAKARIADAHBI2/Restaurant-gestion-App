import firebase from 'firebase';

const firebaseConfig = {
    // Your firebase config from set up
}

firebase.initializeApp(firebaseConfig);

export const auth = firebase.auth();