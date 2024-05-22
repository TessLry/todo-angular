export const constants = {
    CURRENT_TOKEN: 'CURRENT_TOKEN',
};

const apiurl = 'http://localhost:8080'

export const apiEndpoint = {
    AuthEndpoint: {
        login: `${apiurl}/auth/login`,
        logout: `${apiurl}/auth/logout`,
        //loggedUser: `${apiurl}/user`,
    },
    TodoEndpoint: {
        getAllTodo: `${apiurl}/todos`,
        addTodo: `${apiurl}/todo`,
        updateTodo: `${apiurl}/todo`,
        deleteTodo: `${apiurl}/todo`
    }
}