// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';
/** doThumb POST /api/post_thumb/ */
export async function doThumbUsingPOST(body, options) {
    return request('/api/post_thumb/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        data: body,
        ...(options || {}),
    });
}
//# sourceMappingURL=postThumbController.js.map