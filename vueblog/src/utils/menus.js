import {getRequest} from "./api";

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRoutes = formatRoutes(data);
            router.addRoutes(fmtRoutes);
            store.commit('initRoutes', fmtRoutes);
        }
    })
}

export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            icon,
            children
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            icon: icon,
            meta: meta,
            children: children,
            component(resolve) {
                if (component.startsWith("Home")) {
                    require(['../views/' + component + '.vue'], resolve);
                } else if (component.startsWith("User")) {
                    require(['../views/user/' + component + '.vue'], resolve);
                } else if (component.startsWith("Permission")) {
                    require(['../views/permission/' + component + '.vue'], resolve);
                } else if (component.startsWith("Article")) {
                    require(['../views/article/' + component + '.vue'], resolve);
                } else if (component.startsWith("Analysis")) {
                    require(['../views/analysis/' + component + '.vue'], resolve);
                }else if (component.startsWith("System")) {
                    require(['../views/system/' + component + '.vue'], resolve);
                }
            }
        }
        fmRoutes.push(fmRouter);
    })
    return fmRoutes;
}
