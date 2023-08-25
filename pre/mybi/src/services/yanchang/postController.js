// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';
/** addPost POST /api/post/add */
export async function addPostUsingPOST(body, options) {
    return request('/api/post/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** deletePost POST /api/post/delete */
export async function deletePostUsingPOST(body, options) {
    return request('/api/post/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** editPost POST /api/post/edit */
export async function editPostUsingPOST(body, options) {
    return request('/api/post/edit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** listPostVOByPage POST /api/post/list/page/vo */
export async function listPostVOByPageUsingPOST(body, options) {
    return request('/api/post/list/page/vo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** listMyPostVOByPage POST /api/post/my/list/page/vo */
export async function listMyPostVOByPageUsingPOST(body, options) {
    return request('/api/post/my/list/page/vo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** searchPostVOByPage POST /api/post/search/page/vo */
export async function searchPostVOByPageUsingPOST(body, options) {
    return request('/api/post/search/page/vo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
/** getPostVOById POST /api/post/update */
export async function getPostVOByIdUsingPOST(
// 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
params, options) {
    return request('/api/post/update', {
        method: 'POST',
        params: {
            ...params,
        },
        ...(options || {}),
    });
}
//# sourceMappingURL=postController.js.map