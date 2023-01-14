(()=>{var Dt=Object.create;var ot=Object.defineProperty;var Nt=Object.getOwnPropertyDescriptor;var Mt=Object.getOwnPropertyNames;var $t=Object.getPrototypeOf,jt=Object.prototype.hasOwnProperty;var vt=r=>ot(r,"__esModule",{value:!0});var Gt=(r,t)=>()=>(t||r((t={exports:{}}).exports,t),t.exports);var Yt=(r,t,e,n)=>{if(t&&typeof t=="object"||typeof t=="function")for(let i of Mt(t))!jt.call(r,i)&&(e||i!=="default")&&ot(r,i,{get:()=>t[i],enumerable:!(n=Nt(t,i))||n.enumerable});return r},Wt=(r,t)=>Yt(vt(ot(r!=null?Dt($t(r)):{},"default",!t&&r&&r.__esModule?{get:()=>r.default,enumerable:!0}:{value:r,enumerable:!0})),r);var Pt=Gt((X,W)=>{(function(r){if("TextEncoder"in r&&"TextDecoder"in r){let e=r.TextEncoder,n=r.TextDecoder;W&&W.exports?(W.exports.TextEncoder=e,W.exports.TextDecoder=n):X&&(X.TextEncoder=e,X.TextDecoder=n)}else{var t=!1;(function(e){"use strict";var n=String.fromCharCode,i={}.toString,o=i.call(e.SharedArrayBuffer),u="[object Undefined]",l=e.Uint8Array,s=l||Array,y=l?ArrayBuffer:s,p=y.isView||function(U){return U&&"length"in U},a=i.call(y.prototype),b=encodeURIComponent,m=parseInt,C=G.prototype,L=e.TextEncoder,h=/[\xc0-\xff][\x80-\xbf]+|[\x80-\xff]/g,x=/[\x80-\uD7ff\uDC00-\uFFFF]|[\uD800-\uDBFF][\uDC00-\uDFFF]?/g,c=new(l?Uint16Array:s)(32),E,A;function T(){}T.prototype.decode=function(U){var d=U,R;if(!p(d)){if(R=i.call(d),R!==a&&R!==o&&R!==u)throw TypeError("Failed to execute 'decode' on 'TextDecoder': The provided value is not of type '(ArrayBuffer or ArrayBufferView)'");d=l?new s(d):d||[]}for(var I="",F="",g=0,w=d.length|0,B=w-32|0,P=0,it=0,S=0,k=0,q=0,M=0,D=0,$=-1;g<w;){for(P=g<=B?32:w-g|0;D<P;g=g+1|0,D=D+1|0){switch(S=d[g]&255,S>>4){case 15:if(M=d[g=g+1|0]&255,M>>6!==2||247<S){g=g-1|0;break}k=(S&7)<<6|M&63,q=5,S=256;case 14:M=d[g=g+1|0]&255,k<<=6,k|=(S&15)<<6|M&63,q=M>>6===2?q+4|0:24,S=S+256&768;case 13:case 12:M=d[g=g+1|0]&255,k<<=6,k|=(S&31)<<6|M&63,q=q+7|0,g<w&&M>>6===2&&k>>q&&k<1114112?(S=k,k=k-65536|0,0<=k?($=(k>>10)+55296|0,S=(k&1023)+56320|0,D<31?(c[D]=$,D=D+1|0,$=-1):(M=$,$=S,S=M)):P=P+1|0):(S>>=8,g=g-S-1|0,S=65533),q=0,k=0,P=g<=B?32:w-g|0;default:c[D]=S;continue;case 11:case 10:case 9:case 8:}c[D]=65533}if(F+=n(c[0],c[1],c[2],c[3],c[4],c[5],c[6],c[7],c[8],c[9],c[10],c[11],c[12],c[13],c[14],c[15],c[16],c[17],c[18],c[19],c[20],c[21],c[22],c[23],c[24],c[25],c[26],c[27],c[28],c[29],c[30],c[31]),D<32&&(F=F.slice(0,D-32|0)),g<w){if(c[0]=$,D=~$>>>31,$=-1,F.length<I.length)continue}else $!==-1&&(F+=n($));I+=F,F=""}return I};function Z(U){var d=U.charCodeAt(0)|0;if(55296<=d)if(d<=56319){var R=U.charCodeAt(1)|0;if(56320<=R&&R<=57343){if(d=(d<<10)+R-56613888|0,d>65535)return n(30<<3|d>>18,2<<6|d>>12&63,2<<6|d>>6&63,2<<6|d&63)}else d=65533}else d<=57343&&(d=65533);return d<=2047?n(6<<5|d>>6,2<<6|d&63):n(14<<4|d>>12,2<<6|d>>6&63,2<<6|d&63)}function G(){}C.encode=function(U){var d=U===void 0?"":""+U,R=d.length|0,I=new s((R<<1)+8|0),F,g=0,w=0,B=0,P=0,it=!l;for(g=0;g<R;g=g+1|0,w=w+1|0)if(B=d.charCodeAt(g)|0,B<=127)I[w]=B;else if(B<=2047)I[w]=6<<5|B>>6,I[w=w+1|0]=2<<6|B&63;else{t:{if(55296<=B)if(B<=56319){if(P=d.charCodeAt(g=g+1|0)|0,56320<=P&&P<=57343){if(B=(B<<10)+P-56613888|0,B>65535){I[w]=30<<3|B>>18,I[w=w+1|0]=2<<6|B>>12&63,I[w=w+1|0]=2<<6|B>>6&63,I[w=w+1|0]=2<<6|B&63;continue}break t}B=65533}else B<=57343&&(B=65533);!it&&g<<1<w&&g<<1<(w-7|0)&&(it=!0,F=new s(R*3),F.set(I),I=F)}I[w]=14<<4|B>>12,I[w=w+1|0]=2<<6|B>>6&63,I[w=w+1|0]=2<<6|B&63}return l?I.subarray(0,w):I.slice(0,w)};function tt(U,d){var R=U===void 0?"":(""+U).replace(x,Z),I=R.length|0,F=0,g=0,w=0,B=d.length|0,P=U.length|0;B<I&&(I=B);t:for(;F<I;F=F+1|0){switch(g=R.charCodeAt(F)|0,g>>4){case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:w=w+1|0;case 8:case 9:case 10:case 11:break;case 12:case 13:if((F+1|0)<B){w=w+1|0;break}case 14:if((F+2|0)<B){w=w+1|0;break}case 15:if((F+3|0)<B){w=w+1|0;break}default:break t}d[F]=g}return{written:F,read:P<w?P:w}}t&&(C.encodeInto=tt),L?t&&!(E=L.prototype).encodeInto&&(A=new L,E.encodeInto=function(U,d){var R=U.length|0,I=d.length|0;if(R<I>>1){var F=A.encode(U),g=F.length|0;if(g<I)return d.set(F),{read:R,written:F.length|0}}return tt(U,d)}):(e.TextDecoder=T,e.TextEncoder=G,W&&W.exports?(W.exports.TextEncoder=G,W.exports.TextDecoder=T):X&&(X.TextEncoder=G,X.TextDecoder=T))})(globalThis)}})(globalThis)});(function(r){typeof define=="function"&&define.amd?define(r):r()})(function(){"use strict";function r(h,x){if(!(h instanceof x))throw new TypeError("Cannot call a class as a function")}function t(h,x){for(var c=0;c<x.length;c++){var E=x[c];E.enumerable=E.enumerable||!1,E.configurable=!0,"value"in E&&(E.writable=!0),Object.defineProperty(h,E.key,E)}}function e(h,x,c){return x&&t(h.prototype,x),c&&t(h,c),Object.defineProperty(h,"prototype",{writable:!1}),h}function n(h,x){if(typeof x!="function"&&x!==null)throw new TypeError("Super expression must either be null or a function");h.prototype=Object.create(x&&x.prototype,{constructor:{value:h,writable:!0,configurable:!0}}),Object.defineProperty(h,"prototype",{writable:!1}),x&&o(h,x)}function i(h){return i=Object.setPrototypeOf?Object.getPrototypeOf.bind():function(c){return c.__proto__||Object.getPrototypeOf(c)},i(h)}function o(h,x){return o=Object.setPrototypeOf?Object.setPrototypeOf.bind():function(E,A){return E.__proto__=A,E},o(h,x)}function u(){if(typeof Reflect=="undefined"||!Reflect.construct||Reflect.construct.sham)return!1;if(typeof Proxy=="function")return!0;try{return Boolean.prototype.valueOf.call(Reflect.construct(Boolean,[],function(){})),!0}catch{return!1}}function l(h){if(h===void 0)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return h}function s(h,x){if(x&&(typeof x=="object"||typeof x=="function"))return x;if(x!==void 0)throw new TypeError("Derived constructors may only return object or undefined");return l(h)}function y(h){var x=u();return function(){var E=i(h),A;if(x){var T=i(this).constructor;A=Reflect.construct(E,arguments,T)}else A=E.apply(this,arguments);return s(this,A)}}function p(h,x){for(;!Object.prototype.hasOwnProperty.call(h,x)&&(h=i(h),h!==null););return h}function a(){return typeof Reflect!="undefined"&&Reflect.get?a=Reflect.get.bind():a=function(x,c,E){var A=p(x,c);if(!!A){var T=Object.getOwnPropertyDescriptor(A,c);return T.get?T.get.call(arguments.length<3?x:E):T.value}},a.apply(this,arguments)}var b=function(){function h(){r(this,h),Object.defineProperty(this,"listeners",{value:{},writable:!0,configurable:!0})}return e(h,[{key:"addEventListener",value:function(c,E,A){c in this.listeners||(this.listeners[c]=[]),this.listeners[c].push({callback:E,options:A})}},{key:"removeEventListener",value:function(c,E){if(c in this.listeners){for(var A=this.listeners[c],T=0,Z=A.length;T<Z;T++)if(A[T].callback===E){A.splice(T,1);return}}}},{key:"dispatchEvent",value:function(c){if(c.type in this.listeners){for(var E=this.listeners[c.type],A=E.slice(),T=0,Z=A.length;T<Z;T++){var G=A[T];try{G.callback.call(this,c)}catch(tt){Promise.resolve().then(function(){throw tt})}G.options&&G.options.once&&this.removeEventListener(c.type,G.callback)}return!c.defaultPrevented}}}]),h}(),m=function(h){n(c,h);var x=y(c);function c(){var E;return r(this,c),E=x.call(this),E.listeners||b.call(l(E)),Object.defineProperty(l(E),"aborted",{value:!1,writable:!0,configurable:!0}),Object.defineProperty(l(E),"onabort",{value:null,writable:!0,configurable:!0}),E}return e(c,[{key:"toString",value:function(){return"[object AbortSignal]"}},{key:"dispatchEvent",value:function(A){A.type==="abort"&&(this.aborted=!0,typeof this.onabort=="function"&&this.onabort.call(this,A)),a(i(c.prototype),"dispatchEvent",this).call(this,A)}}]),c}(b),C=function(){function h(){r(this,h),Object.defineProperty(this,"signal",{value:new m,writable:!0,configurable:!0})}return e(h,[{key:"abort",value:function(){var c;try{c=new Event("abort")}catch{typeof document!="undefined"?document.createEvent?(c=document.createEvent("Event"),c.initEvent("abort",!1,!1)):(c=document.createEventObject(),c.type="abort"):c={type:"abort",bubbles:!1,cancelable:!1}}this.signal.dispatchEvent(c)}},{key:"toString",value:function(){return"[object AbortController]"}}]),h}();typeof Symbol!="undefined"&&Symbol.toStringTag&&(C.prototype[Symbol.toStringTag]="AbortController",m.prototype[Symbol.toStringTag]="AbortSignal");function L(h){return h.__FORCE_INSTALL_ABORTCONTROLLER_POLYFILL?!0:!h.AbortController}(function(h){!L(h)||(h.AbortController=C,h.AbortSignal=m)})(typeof self!="undefined"?self:globalThis)});var Vt=globalThis.AbortController,qt=globalThis.AbortSignal,_e={AbortController:Vt,AbortSignal:qt};var et={byteLength:Ht,toByteArray:Jt,fromByteArray:Qt},j=[],N=[],zt=typeof Uint8Array!="undefined"?Uint8Array:Array,ut="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";for(let r=0,t=ut.length;r<t;++r)j[r]=ut[r],N[ut.charCodeAt(r)]=r;N["-".charCodeAt(0)]=62,N["_".charCodeAt(0)]=63;function yt(r){let t=r.length;if(t%4>0)throw new Error("Invalid string. Length must be a multiple of 4");let e=r.indexOf("=");e===-1&&(e=t);let n=e===t?0:4-e%4;return[e,n]}function Ht(r){let t=yt(r),e=t[0],n=t[1];return(e+n)*3/4-n}function Xt(r,t,e){return(t+e)*3/4-e}function Jt(r){let t,e=yt(r),n=e[0],i=e[1],o=new zt(Xt(r,n,i)),u=0,l=i>0?n-4:n,s;for(s=0;s<l;s+=4)t=N[r.charCodeAt(s)]<<18|N[r.charCodeAt(s+1)]<<12|N[r.charCodeAt(s+2)]<<6|N[r.charCodeAt(s+3)],o[u++]=t>>16&255,o[u++]=t>>8&255,o[u++]=t&255;return i===2&&(t=N[r.charCodeAt(s)]<<2|N[r.charCodeAt(s+1)]>>4,o[u++]=t&255),i===1&&(t=N[r.charCodeAt(s)]<<10|N[r.charCodeAt(s+1)]<<4|N[r.charCodeAt(s+2)]>>2,o[u++]=t>>8&255,o[u++]=t&255),o}function Kt(r){return j[r>>18&63]+j[r>>12&63]+j[r>>6&63]+j[r&63]}function Zt(r,t,e){let n,i=[];for(let o=t;o<e;o+=3)n=(r[o]<<16&16711680)+(r[o+1]<<8&65280)+(r[o+2]&255),i.push(Kt(n));return i.join("")}function Qt(r){let t,e=r.length,n=e%3,i=[],o=16383;for(let u=0,l=e-n;u<l;u+=o)i.push(Zt(r,u,u+o>l?l:u+o));return n===1?(t=r[e-1],i.push(j[t>>2]+j[t<<4&63]+"==")):n===2&&(t=(r[e-2]<<8)+r[e-1],i.push(j[t>>10]+j[t>>4&63]+j[t<<2&63]+"=")),i.join("")}var z={read:te,write:ee},te=function(r,t,e,n,i){var o,u,l=i*8-n-1,s=(1<<l)-1,y=s>>1,p=-7,a=e?i-1:0,b=e?-1:1,m=r[t+a];for(a+=b,o=m&(1<<-p)-1,m>>=-p,p+=l;p>0;o=o*256+r[t+a],a+=b,p-=8);for(u=o&(1<<-p)-1,o>>=-p,p+=n;p>0;u=u*256+r[t+a],a+=b,p-=8);if(o===0)o=1-y;else{if(o===s)return u?NaN:(m?-1:1)*(1/0);u=u+Math.pow(2,n),o=o-y}return(m?-1:1)*u*Math.pow(2,o-n)},ee=function(r,t,e,n,i,o){var u,l,s,y=o*8-i-1,p=(1<<y)-1,a=p>>1,b=i===23?Math.pow(2,-24)-Math.pow(2,-77):0,m=n?0:o-1,C=n?1:-1,L=t<0||t===0&&1/t<0?1:0;for(t=Math.abs(t),isNaN(t)||t===1/0?(l=isNaN(t)?1:0,u=p):(u=Math.floor(Math.log(t)/Math.LN2),t*(s=Math.pow(2,-u))<1&&(u--,s*=2),u+a>=1?t+=b/s:t+=b*Math.pow(2,1-a),t*s>=2&&(u++,s/=2),u+a>=p?(l=0,u=p):u+a>=1?(l=(t*s-1)*Math.pow(2,i),u=u+a):(l=t*Math.pow(2,a-1)*Math.pow(2,i),u=0));i>=8;r[e+m]=l&255,m+=C,l/=256,i-=8);for(u=u<<i|l,y+=i;y>0;r[e+m]=u&255,m+=C,u/=256,y-=8);r[e+m-C]|=L*128};var xt=typeof Symbol=="function"&&typeof Symbol.for=="function"?Symbol.for("nodejs.util.inspect.custom"):null,rt=2147483647,dt=50;f.TYPED_ARRAY_SUPPORT=re(),!f.TYPED_ARRAY_SUPPORT&&typeof console!="undefined"&&typeof console.error=="function";function re(){try{let r=new Uint8Array(1),t={foo:function(){return 42}};return Object.setPrototypeOf(t,Uint8Array.prototype),Object.setPrototypeOf(r,t),r.foo()===42}catch{return!1}}Object.defineProperty(f.prototype,"parent",{enumerable:!0,get:function(){if(!!f.isBuffer(this))return this.buffer}}),Object.defineProperty(f.prototype,"offset",{enumerable:!0,get:function(){if(!!f.isBuffer(this))return this.byteOffset}});function Y(r){if(r>rt)throw new RangeError('The value "'+r+'" is invalid for option "size"');let t=new Uint8Array(r);return Object.setPrototypeOf(t,f.prototype),t}function f(r,t,e){if(typeof r=="number"){if(typeof t=="string")throw new TypeError('The "string" argument must be of type string. Received type number');return ft(r)}return wt(r,t,e)}f.poolSize=8192;function wt(r,t,e){if(typeof r=="string")return ie(r,t);if(ArrayBuffer.isView(r))return oe(r);if(r==null)throw new TypeError("The first argument must be one of type string, Buffer, ArrayBuffer, Array, or Array-like Object. Received type "+typeof r);if(v(r,ArrayBuffer)||r&&v(r.buffer,ArrayBuffer)||typeof SharedArrayBuffer!="undefined"&&(v(r,SharedArrayBuffer)||r&&v(r.buffer,SharedArrayBuffer)))return lt(r,t,e);if(typeof r=="number")throw new TypeError('The "value" argument must not be of type number. Received type number');let n=r.valueOf&&r.valueOf();if(n!=null&&n!==r)return f.from(n,t,e);let i=ue(r);if(i)return i;if(typeof Symbol!="undefined"&&Symbol.toPrimitive!=null&&typeof r[Symbol.toPrimitive]=="function")return f.from(r[Symbol.toPrimitive]("string"),t,e);throw new TypeError("The first argument must be one of type string, Buffer, ArrayBuffer, Array, or Array-like Object. Received type "+typeof r)}f.from=function(r,t,e){return wt(r,t,e)},Object.setPrototypeOf(f.prototype,Uint8Array.prototype),Object.setPrototypeOf(f,Uint8Array);function gt(r){if(typeof r!="number")throw new TypeError('"size" argument must be of type number');if(r<0)throw new RangeError('The value "'+r+'" is invalid for option "size"')}function ne(r,t,e){return gt(r),r<=0?Y(r):t!==void 0?typeof e=="string"?Y(r).fill(t,e):Y(r).fill(t):Y(r)}f.alloc=function(r,t,e){return ne(r,t,e)};function ft(r){return gt(r),Y(r<0?0:at(r)|0)}f.allocUnsafe=function(r){return ft(r)},f.allocUnsafeSlow=function(r){return ft(r)};function ie(r,t){if((typeof t!="string"||t==="")&&(t="utf8"),!f.isEncoding(t))throw new TypeError("Unknown encoding: "+t);let e=Bt(r,t)|0,n=Y(e),i=n.write(r,t);return i!==e&&(n=n.slice(0,i)),n}function ct(r){let t=r.length<0?0:at(r.length)|0,e=Y(t);for(let n=0;n<t;n+=1)e[n]=r[n]&255;return e}function oe(r){if(v(r,Uint8Array)){let t=new Uint8Array(r);return lt(t.buffer,t.byteOffset,t.byteLength)}return ct(r)}function lt(r,t,e){if(t<0||r.byteLength<t)throw new RangeError('"offset" is outside of buffer bounds');if(r.byteLength<t+(e||0))throw new RangeError('"length" is outside of buffer bounds');let n;return t===void 0&&e===void 0?n=new Uint8Array(r):e===void 0?n=new Uint8Array(r,t):n=new Uint8Array(r,t,e),Object.setPrototypeOf(n,f.prototype),n}function ue(r){if(f.isBuffer(r)){let t=at(r.length)|0,e=Y(t);return e.length===0||r.copy(e,0,0,t),e}if(r.length!==void 0)return typeof r.length!="number"||pt(r.length)?Y(0):ct(r);if(r.type==="Buffer"&&Array.isArray(r.data))return ct(r.data)}function at(r){if(r>=rt)throw new RangeError("Attempt to allocate Buffer larger than maximum size: 0x"+rt.toString(16)+" bytes");return r|0}function Et(r){return+r!=r&&(r=0),f.alloc(+r)}f.isBuffer=function(t){return t!=null&&t._isBuffer===!0&&t!==f.prototype},f.compare=function(t,e){if(v(t,Uint8Array)&&(t=f.from(t,t.offset,t.byteLength)),v(e,Uint8Array)&&(e=f.from(e,e.offset,e.byteLength)),!f.isBuffer(t)||!f.isBuffer(e))throw new TypeError('The "buf1", "buf2" arguments must be one of type Buffer or Uint8Array');if(t===e)return 0;let n=t.length,i=e.length;for(let o=0,u=Math.min(n,i);o<u;++o)if(t[o]!==e[o]){n=t[o],i=e[o];break}return n<i?-1:i<n?1:0},f.isEncoding=function(t){switch(String(t).toLowerCase()){case"hex":case"utf8":case"utf-8":case"ascii":case"latin1":case"binary":case"base64":case"ucs2":case"ucs-2":case"utf16le":case"utf-16le":return!0;default:return!1}},f.concat=function(t,e){if(!Array.isArray(t))throw new TypeError('"list" argument must be an Array of Buffers');if(t.length===0)return f.alloc(0);let n;if(e===void 0)for(e=0,n=0;n<t.length;++n)e+=t[n].length;let i=f.allocUnsafe(e),o=0;for(n=0;n<t.length;++n){let u=t[n];if(v(u,Uint8Array))o+u.length>i.length?(f.isBuffer(u)||(u=f.from(u)),u.copy(i,o)):Uint8Array.prototype.set.call(i,u,o);else if(f.isBuffer(u))u.copy(i,o);else throw new TypeError('"list" argument must be an Array of Buffers');o+=u.length}return i};function Bt(r,t){if(f.isBuffer(r))return r.length;if(ArrayBuffer.isView(r)||v(r,ArrayBuffer))return r.byteLength;if(typeof r!="string")throw new TypeError('The "string" argument must be one of type string, Buffer, or ArrayBuffer. Received type '+typeof r);let e=r.length,n=arguments.length>2&&arguments[2]===!0;if(!n&&e===0)return 0;let i=!1;for(;;)switch(t){case"ascii":case"latin1":case"binary":return e;case"utf8":case"utf-8":return ht(r).length;case"ucs2":case"ucs-2":case"utf16le":case"utf-16le":return e*2;case"hex":return e>>>1;case"base64":return Lt(r).length;default:if(i)return n?-1:ht(r).length;t=(""+t).toLowerCase(),i=!0}}f.byteLength=Bt;function fe(r,t,e){let n=!1;if((t===void 0||t<0)&&(t=0),t>this.length||((e===void 0||e>this.length)&&(e=this.length),e<=0)||(e>>>=0,t>>>=0,e<=t))return"";for(r||(r="utf8");;)switch(r){case"hex":return we(this,t,e);case"utf8":case"utf-8":return Ft(this,t,e);case"ascii":return xe(this,t,e);case"latin1":case"binary":return de(this,t,e);case"base64":return pe(this,t,e);case"ucs2":case"ucs-2":case"utf16le":case"utf-16le":return ge(this,t,e);default:if(n)throw new TypeError("Unknown encoding: "+r);r=(r+"").toLowerCase(),n=!0}}f.prototype._isBuffer=!0;function H(r,t,e){let n=r[t];r[t]=r[e],r[e]=n}f.prototype.swap16=function(){let t=this.length;if(t%2!==0)throw new RangeError("Buffer size must be a multiple of 16-bits");for(let e=0;e<t;e+=2)H(this,e,e+1);return this},f.prototype.swap32=function(){let t=this.length;if(t%4!==0)throw new RangeError("Buffer size must be a multiple of 32-bits");for(let e=0;e<t;e+=4)H(this,e,e+3),H(this,e+1,e+2);return this},f.prototype.swap64=function(){let t=this.length;if(t%8!==0)throw new RangeError("Buffer size must be a multiple of 64-bits");for(let e=0;e<t;e+=8)H(this,e,e+7),H(this,e+1,e+6),H(this,e+2,e+5),H(this,e+3,e+4);return this},f.prototype.toString=function(){let t=this.length;return t===0?"":arguments.length===0?Ft(this,0,t):fe.apply(this,arguments)},f.prototype.toLocaleString=f.prototype.toString,f.prototype.equals=function(t){if(!f.isBuffer(t))throw new TypeError("Argument must be a Buffer");return this===t?!0:f.compare(this,t)===0},f.prototype.inspect=function(){let t="",e=dt;return t=this.toString("hex",0,e).replace(/(.{2})/g,"$1 ").trim(),this.length>e&&(t+=" ... "),"<Buffer "+t+">"},xt&&(f.prototype[xt]=f.prototype.inspect),f.prototype.compare=function(t,e,n,i,o){if(v(t,Uint8Array)&&(t=f.from(t,t.offset,t.byteLength)),!f.isBuffer(t))throw new TypeError('The "target" argument must be one of type Buffer or Uint8Array. Received type '+typeof t);if(e===void 0&&(e=0),n===void 0&&(n=t?t.length:0),i===void 0&&(i=0),o===void 0&&(o=this.length),e<0||n>t.length||i<0||o>this.length)throw new RangeError("out of range index");if(i>=o&&e>=n)return 0;if(i>=o)return-1;if(e>=n)return 1;if(e>>>=0,n>>>=0,i>>>=0,o>>>=0,this===t)return 0;let u=o-i,l=n-e,s=Math.min(u,l),y=this.slice(i,o),p=t.slice(e,n);for(let a=0;a<s;++a)if(y[a]!==p[a]){u=y[a],l=p[a];break}return u<l?-1:l<u?1:0};function mt(r,t,e,n,i){if(r.length===0)return-1;if(typeof e=="string"?(n=e,e=0):e>2147483647?e=2147483647:e<-2147483648&&(e=-2147483648),e=+e,pt(e)&&(e=i?0:r.length-1),e<0&&(e=r.length+e),e>=r.length){if(i)return-1;e=r.length-1}else if(e<0)if(i)e=0;else return-1;if(typeof t=="string"&&(t=f.from(t,n)),f.isBuffer(t))return t.length===0?-1:It(r,t,e,n,i);if(typeof t=="number")return t=t&255,typeof Uint8Array.prototype.indexOf=="function"?i?Uint8Array.prototype.indexOf.call(r,t,e):Uint8Array.prototype.lastIndexOf.call(r,t,e):It(r,[t],e,n,i);throw new TypeError("val must be string, number or Buffer")}function It(r,t,e,n,i){let o=1,u=r.length,l=t.length;if(n!==void 0&&(n=String(n).toLowerCase(),n==="ucs2"||n==="ucs-2"||n==="utf16le"||n==="utf-16le")){if(r.length<2||t.length<2)return-1;o=2,u/=2,l/=2,e/=2}function s(p,a){return o===1?p[a]:p.readUInt16BE(a*o)}let y;if(i){let p=-1;for(y=e;y<u;y++)if(s(r,y)===s(t,p===-1?0:y-p)){if(p===-1&&(p=y),y-p+1===l)return p*o}else p!==-1&&(y-=y-p),p=-1}else for(e+l>u&&(e=u-l),y=e;y>=0;y--){let p=!0;for(let a=0;a<l;a++)if(s(r,y+a)!==s(t,a)){p=!1;break}if(p)return y}return-1}f.prototype.includes=function(t,e,n){return this.indexOf(t,e,n)!==-1},f.prototype.indexOf=function(t,e,n){return mt(this,t,e,n,!0)},f.prototype.lastIndexOf=function(t,e,n){return mt(this,t,e,n,!1)};function ce(r,t,e,n){e=Number(e)||0;let i=r.length-e;n?(n=Number(n),n>i&&(n=i)):n=i;let o=t.length;n>o/2&&(n=o/2);let u;for(u=0;u<n;++u){let l=parseInt(t.substr(u*2,2),16);if(pt(l))return u;r[e+u]=l}return u}function le(r,t,e,n){return nt(ht(t,r.length-e),r,e,n)}function ae(r,t,e,n){return nt(Ie(t),r,e,n)}function se(r,t,e,n){return nt(Lt(t),r,e,n)}function he(r,t,e,n){return nt(Fe(t,r.length-e),r,e,n)}f.prototype.write=function(t,e,n,i){if(e===void 0)i="utf8",n=this.length,e=0;else if(n===void 0&&typeof e=="string")i=e,n=this.length,e=0;else if(isFinite(e))e=e>>>0,isFinite(n)?(n=n>>>0,i===void 0&&(i="utf8")):(i=n,n=void 0);else throw new Error("Buffer.write(string, encoding, offset[, length]) is no longer supported");let o=this.length-e;if((n===void 0||n>o)&&(n=o),t.length>0&&(n<0||e<0)||e>this.length)throw new RangeError("Attempt to write outside buffer bounds");i||(i="utf8");let u=!1;for(;;)switch(i){case"hex":return ce(this,t,e,n);case"utf8":case"utf-8":return le(this,t,e,n);case"ascii":case"latin1":case"binary":return ae(this,t,e,n);case"base64":return se(this,t,e,n);case"ucs2":case"ucs-2":case"utf16le":case"utf-16le":return he(this,t,e,n);default:if(u)throw new TypeError("Unknown encoding: "+i);i=(""+i).toLowerCase(),u=!0}},f.prototype.toJSON=function(){return{type:"Buffer",data:Array.prototype.slice.call(this._arr||this,0)}};function pe(r,t,e){return t===0&&e===r.length?et.fromByteArray(r):et.fromByteArray(r.slice(t,e))}function Ft(r,t,e){e=Math.min(r.length,e);let n=[],i=t;for(;i<e;){let o=r[i],u=null,l=o>239?4:o>223?3:o>191?2:1;if(i+l<=e){let s,y,p,a;switch(l){case 1:o<128&&(u=o);break;case 2:s=r[i+1],(s&192)===128&&(a=(o&31)<<6|s&63,a>127&&(u=a));break;case 3:s=r[i+1],y=r[i+2],(s&192)===128&&(y&192)===128&&(a=(o&15)<<12|(s&63)<<6|y&63,a>2047&&(a<55296||a>57343)&&(u=a));break;case 4:s=r[i+1],y=r[i+2],p=r[i+3],(s&192)===128&&(y&192)===128&&(p&192)===128&&(a=(o&15)<<18|(s&63)<<12|(y&63)<<6|p&63,a>65535&&a<1114112&&(u=a))}}u===null?(u=65533,l=1):u>65535&&(u-=65536,n.push(u>>>10&1023|55296),u=56320|u&1023),n.push(u),i+=l}return ye(n)}var At=4096;function ye(r){let t=r.length;if(t<=At)return String.fromCharCode.apply(String,r);let e="",n=0;for(;n<t;)e+=String.fromCharCode.apply(String,r.slice(n,n+=At));return e}function xe(r,t,e){let n="";e=Math.min(r.length,e);for(let i=t;i<e;++i)n+=String.fromCharCode(r[i]&127);return n}function de(r,t,e){let n="";e=Math.min(r.length,e);for(let i=t;i<e;++i)n+=String.fromCharCode(r[i]);return n}function we(r,t,e){let n=r.length;(!t||t<0)&&(t=0),(!e||e<0||e>n)&&(e=n);let i="";for(let o=t;o<e;++o)i+=Ae[r[o]];return i}function ge(r,t,e){let n=r.slice(t,e),i="";for(let o=0;o<n.length-1;o+=2)i+=String.fromCharCode(n[o]+n[o+1]*256);return i}f.prototype.slice=function(t,e){let n=this.length;t=~~t,e=e===void 0?n:~~e,t<0?(t+=n,t<0&&(t=0)):t>n&&(t=n),e<0?(e+=n,e<0&&(e=0)):e>n&&(e=n),e<t&&(e=t);let i=this.subarray(t,e);return Object.setPrototypeOf(i,f.prototype),i};function _(r,t,e){if(r%1!==0||r<0)throw new RangeError("offset is not uint");if(r+t>e)throw new RangeError("Trying to access beyond buffer length")}f.prototype.readUintLE=f.prototype.readUIntLE=function(t,e,n){t=t>>>0,e=e>>>0,n||_(t,e,this.length);let i=this[t],o=1,u=0;for(;++u<e&&(o*=256);)i+=this[t+u]*o;return i},f.prototype.readUintBE=f.prototype.readUIntBE=function(t,e,n){t=t>>>0,e=e>>>0,n||_(t,e,this.length);let i=this[t+--e],o=1;for(;e>0&&(o*=256);)i+=this[t+--e]*o;return i},f.prototype.readUint8=f.prototype.readUInt8=function(t,e){return t=t>>>0,e||_(t,1,this.length),this[t]},f.prototype.readUint16LE=f.prototype.readUInt16LE=function(t,e){return t=t>>>0,e||_(t,2,this.length),this[t]|this[t+1]<<8},f.prototype.readUint16BE=f.prototype.readUInt16BE=function(t,e){return t=t>>>0,e||_(t,2,this.length),this[t]<<8|this[t+1]},f.prototype.readUint32LE=f.prototype.readUInt32LE=function(t,e){return t=t>>>0,e||_(t,4,this.length),(this[t]|this[t+1]<<8|this[t+2]<<16)+this[t+3]*16777216},f.prototype.readUint32BE=f.prototype.readUInt32BE=function(t,e){return t=t>>>0,e||_(t,4,this.length),this[t]*16777216+(this[t+1]<<16|this[t+2]<<8|this[t+3])},f.prototype.readBigUInt64LE=V(function(t){t=t>>>0,K(t,"offset");let e=this[t],n=this[t+7];(e===void 0||n===void 0)&&Q(t,this.length-8);let i=e+this[++t]*2**8+this[++t]*2**16+this[++t]*2**24,o=this[++t]+this[++t]*2**8+this[++t]*2**16+n*2**24;return BigInt(i)+(BigInt(o)<<BigInt(32))}),f.prototype.readBigUInt64BE=V(function(t){t=t>>>0,K(t,"offset");let e=this[t],n=this[t+7];(e===void 0||n===void 0)&&Q(t,this.length-8);let i=e*2**24+this[++t]*2**16+this[++t]*2**8+this[++t],o=this[++t]*2**24+this[++t]*2**16+this[++t]*2**8+n;return(BigInt(i)<<BigInt(32))+BigInt(o)}),f.prototype.readIntLE=function(t,e,n){t=t>>>0,e=e>>>0,n||_(t,e,this.length);let i=this[t],o=1,u=0;for(;++u<e&&(o*=256);)i+=this[t+u]*o;return o*=128,i>=o&&(i-=Math.pow(2,8*e)),i},f.prototype.readIntBE=function(t,e,n){t=t>>>0,e=e>>>0,n||_(t,e,this.length);let i=e,o=1,u=this[t+--i];for(;i>0&&(o*=256);)u+=this[t+--i]*o;return o*=128,u>=o&&(u-=Math.pow(2,8*e)),u},f.prototype.readInt8=function(t,e){return t=t>>>0,e||_(t,1,this.length),this[t]&128?(255-this[t]+1)*-1:this[t]},f.prototype.readInt16LE=function(t,e){t=t>>>0,e||_(t,2,this.length);let n=this[t]|this[t+1]<<8;return n&32768?n|4294901760:n},f.prototype.readInt16BE=function(t,e){t=t>>>0,e||_(t,2,this.length);let n=this[t+1]|this[t]<<8;return n&32768?n|4294901760:n},f.prototype.readInt32LE=function(t,e){return t=t>>>0,e||_(t,4,this.length),this[t]|this[t+1]<<8|this[t+2]<<16|this[t+3]<<24},f.prototype.readInt32BE=function(t,e){return t=t>>>0,e||_(t,4,this.length),this[t]<<24|this[t+1]<<16|this[t+2]<<8|this[t+3]},f.prototype.readBigInt64LE=V(function(t){t=t>>>0,K(t,"offset");let e=this[t],n=this[t+7];(e===void 0||n===void 0)&&Q(t,this.length-8);let i=this[t+4]+this[t+5]*2**8+this[t+6]*2**16+(n<<24);return(BigInt(i)<<BigInt(32))+BigInt(e+this[++t]*2**8+this[++t]*2**16+this[++t]*2**24)}),f.prototype.readBigInt64BE=V(function(t){t=t>>>0,K(t,"offset");let e=this[t],n=this[t+7];(e===void 0||n===void 0)&&Q(t,this.length-8);let i=(e<<24)+this[++t]*2**16+this[++t]*2**8+this[++t];return(BigInt(i)<<BigInt(32))+BigInt(this[++t]*2**24+this[++t]*2**16+this[++t]*2**8+n)}),f.prototype.readFloatLE=function(t,e){return t=t>>>0,e||_(t,4,this.length),z.read(this,t,!0,23,4)},f.prototype.readFloatBE=function(t,e){return t=t>>>0,e||_(t,4,this.length),z.read(this,t,!1,23,4)},f.prototype.readDoubleLE=function(t,e){return t=t>>>0,e||_(t,8,this.length),z.read(this,t,!0,52,8)},f.prototype.readDoubleBE=function(t,e){return t=t>>>0,e||_(t,8,this.length),z.read(this,t,!1,52,8)};function O(r,t,e,n,i,o){if(!f.isBuffer(r))throw new TypeError('"buffer" argument must be a Buffer instance');if(t>i||t<o)throw new RangeError('"value" argument is out of bounds');if(e+n>r.length)throw new RangeError("Index out of range")}f.prototype.writeUintLE=f.prototype.writeUIntLE=function(t,e,n,i){if(t=+t,e=e>>>0,n=n>>>0,!i){let l=Math.pow(2,8*n)-1;O(this,t,e,n,l,0)}let o=1,u=0;for(this[e]=t&255;++u<n&&(o*=256);)this[e+u]=t/o&255;return e+n},f.prototype.writeUintBE=f.prototype.writeUIntBE=function(t,e,n,i){if(t=+t,e=e>>>0,n=n>>>0,!i){let l=Math.pow(2,8*n)-1;O(this,t,e,n,l,0)}let o=n-1,u=1;for(this[e+o]=t&255;--o>=0&&(u*=256);)this[e+o]=t/u&255;return e+n},f.prototype.writeUint8=f.prototype.writeUInt8=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,1,255,0),this[e]=t&255,e+1},f.prototype.writeUint16LE=f.prototype.writeUInt16LE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,2,65535,0),this[e]=t&255,this[e+1]=t>>>8,e+2},f.prototype.writeUint16BE=f.prototype.writeUInt16BE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,2,65535,0),this[e]=t>>>8,this[e+1]=t&255,e+2},f.prototype.writeUint32LE=f.prototype.writeUInt32LE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,4,4294967295,0),this[e+3]=t>>>24,this[e+2]=t>>>16,this[e+1]=t>>>8,this[e]=t&255,e+4},f.prototype.writeUint32BE=f.prototype.writeUInt32BE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,4,4294967295,0),this[e]=t>>>24,this[e+1]=t>>>16,this[e+2]=t>>>8,this[e+3]=t&255,e+4};function bt(r,t,e,n,i){Ct(t,n,i,r,e,7);let o=Number(t&BigInt(4294967295));r[e++]=o,o=o>>8,r[e++]=o,o=o>>8,r[e++]=o,o=o>>8,r[e++]=o;let u=Number(t>>BigInt(32)&BigInt(4294967295));return r[e++]=u,u=u>>8,r[e++]=u,u=u>>8,r[e++]=u,u=u>>8,r[e++]=u,e}function Tt(r,t,e,n,i){Ct(t,n,i,r,e,7);let o=Number(t&BigInt(4294967295));r[e+7]=o,o=o>>8,r[e+6]=o,o=o>>8,r[e+5]=o,o=o>>8,r[e+4]=o;let u=Number(t>>BigInt(32)&BigInt(4294967295));return r[e+3]=u,u=u>>8,r[e+2]=u,u=u>>8,r[e+1]=u,u=u>>8,r[e]=u,e+8}f.prototype.writeBigUInt64LE=V(function(t,e=0){return bt(this,t,e,BigInt(0),BigInt("0xffffffffffffffff"))}),f.prototype.writeBigUInt64BE=V(function(t,e=0){return Tt(this,t,e,BigInt(0),BigInt("0xffffffffffffffff"))}),f.prototype.writeIntLE=function(t,e,n,i){if(t=+t,e=e>>>0,!i){let s=Math.pow(2,8*n-1);O(this,t,e,n,s-1,-s)}let o=0,u=1,l=0;for(this[e]=t&255;++o<n&&(u*=256);)t<0&&l===0&&this[e+o-1]!==0&&(l=1),this[e+o]=(t/u>>0)-l&255;return e+n},f.prototype.writeIntBE=function(t,e,n,i){if(t=+t,e=e>>>0,!i){let s=Math.pow(2,8*n-1);O(this,t,e,n,s-1,-s)}let o=n-1,u=1,l=0;for(this[e+o]=t&255;--o>=0&&(u*=256);)t<0&&l===0&&this[e+o+1]!==0&&(l=1),this[e+o]=(t/u>>0)-l&255;return e+n},f.prototype.writeInt8=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,1,127,-128),t<0&&(t=255+t+1),this[e]=t&255,e+1},f.prototype.writeInt16LE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,2,32767,-32768),this[e]=t&255,this[e+1]=t>>>8,e+2},f.prototype.writeInt16BE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,2,32767,-32768),this[e]=t>>>8,this[e+1]=t&255,e+2},f.prototype.writeInt32LE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,4,2147483647,-2147483648),this[e]=t&255,this[e+1]=t>>>8,this[e+2]=t>>>16,this[e+3]=t>>>24,e+4},f.prototype.writeInt32BE=function(t,e,n){return t=+t,e=e>>>0,n||O(this,t,e,4,2147483647,-2147483648),t<0&&(t=4294967295+t+1),this[e]=t>>>24,this[e+1]=t>>>16,this[e+2]=t>>>8,this[e+3]=t&255,e+4},f.prototype.writeBigInt64LE=V(function(t,e=0){return bt(this,t,e,-BigInt("0x8000000000000000"),BigInt("0x7fffffffffffffff"))}),f.prototype.writeBigInt64BE=V(function(t,e=0){return Tt(this,t,e,-BigInt("0x8000000000000000"),BigInt("0x7fffffffffffffff"))});function Ut(r,t,e,n,i,o){if(e+n>r.length)throw new RangeError("Index out of range");if(e<0)throw new RangeError("Index out of range")}function Rt(r,t,e,n,i){return t=+t,e=e>>>0,i||Ut(r,t,e,4,34028234663852886e22,-34028234663852886e22),z.write(r,t,e,n,23,4),e+4}f.prototype.writeFloatLE=function(t,e,n){return Rt(this,t,e,!0,n)},f.prototype.writeFloatBE=function(t,e,n){return Rt(this,t,e,!1,n)};function _t(r,t,e,n,i){return t=+t,e=e>>>0,i||Ut(r,t,e,8,17976931348623157e292,-17976931348623157e292),z.write(r,t,e,n,52,8),e+8}f.prototype.writeDoubleLE=function(t,e,n){return _t(this,t,e,!0,n)},f.prototype.writeDoubleBE=function(t,e,n){return _t(this,t,e,!1,n)},f.prototype.copy=function(t,e,n,i){if(!f.isBuffer(t))throw new TypeError("argument should be a Buffer");if(n||(n=0),!i&&i!==0&&(i=this.length),e>=t.length&&(e=t.length),e||(e=0),i>0&&i<n&&(i=n),i===n||t.length===0||this.length===0)return 0;if(e<0)throw new RangeError("targetStart out of bounds");if(n<0||n>=this.length)throw new RangeError("Index out of range");if(i<0)throw new RangeError("sourceEnd out of bounds");i>this.length&&(i=this.length),t.length-e<i-n&&(i=t.length-e+n);let o=i-n;return this===t&&typeof Uint8Array.prototype.copyWithin=="function"?this.copyWithin(e,n,i):Uint8Array.prototype.set.call(t,this.subarray(n,i),e),o},f.prototype.fill=function(t,e,n,i){if(typeof t=="string"){if(typeof e=="string"?(i=e,e=0,n=this.length):typeof n=="string"&&(i=n,n=this.length),i!==void 0&&typeof i!="string")throw new TypeError("encoding must be a string");if(typeof i=="string"&&!f.isEncoding(i))throw new TypeError("Unknown encoding: "+i);if(t.length===1){let u=t.charCodeAt(0);(i==="utf8"&&u<128||i==="latin1")&&(t=u)}}else typeof t=="number"?t=t&255:typeof t=="boolean"&&(t=Number(t));if(e<0||this.length<e||this.length<n)throw new RangeError("Out of range index");if(n<=e)return this;e=e>>>0,n=n===void 0?this.length:n>>>0,t||(t=0);let o;if(typeof t=="number")for(o=e;o<n;++o)this[o]=t;else{let u=f.isBuffer(t)?t:f.from(t,i),l=u.length;if(l===0)throw new TypeError('The value "'+t+'" is invalid for argument "value"');for(o=0;o<n-e;++o)this[o+e]=u[o%l]}return this};var J={};function st(r,t,e){J[r]=class extends e{constructor(){super();Object.defineProperty(this,"message",{value:t.apply(this,arguments),writable:!0,configurable:!0}),this.name=`${this.name} [${r}]`,this.stack,delete this.name}get code(){return r}set code(i){Object.defineProperty(this,"code",{configurable:!0,enumerable:!0,value:i,writable:!0})}toString(){return`${this.name} [${r}]: ${this.message}`}}}st("ERR_BUFFER_OUT_OF_BOUNDS",function(r){return r?`${r} is outside of buffer bounds`:"Attempt to access memory outside buffer bounds"},RangeError),st("ERR_INVALID_ARG_TYPE",function(r,t){return`The "${r}" argument must be of type number. Received type ${typeof t}`},TypeError),st("ERR_OUT_OF_RANGE",function(r,t,e){let n=`The value of "${r}" is out of range.`,i=e;return Number.isInteger(e)&&Math.abs(e)>2**32?i=St(String(e)):typeof e=="bigint"&&(i=String(e),(e>BigInt(2)**BigInt(32)||e<-(BigInt(2)**BigInt(32)))&&(i=St(i)),i+="n"),n+=` It must be ${t}. Received ${i}`,n},RangeError);function St(r){let t="",e=r.length,n=r[0]==="-"?1:0;for(;e>=n+4;e-=3)t=`_${r.slice(e-3,e)}${t}`;return`${r.slice(0,e)}${t}`}function Ee(r,t,e){K(t,"offset"),(r[t]===void 0||r[t+e]===void 0)&&Q(t,r.length-(e+1))}function Ct(r,t,e,n,i,o){if(r>e||r<t){let u=typeof t=="bigint"?"n":"",l;throw o>3?t===0||t===BigInt(0)?l=`>= 0${u} and < 2${u} ** ${(o+1)*8}${u}`:l=`>= -(2${u} ** ${(o+1)*8-1}${u}) and < 2 ** ${(o+1)*8-1}${u}`:l=`>= ${t}${u} and <= ${e}${u}`,new J.ERR_OUT_OF_RANGE("value",l,r)}Ee(n,i,o)}function K(r,t){if(typeof r!="number")throw new J.ERR_INVALID_ARG_TYPE(t,"number",r)}function Q(r,t,e){throw Math.floor(r)!==r?(K(r,e),new J.ERR_OUT_OF_RANGE(e||"offset","an integer",r)):t<0?new J.ERR_BUFFER_OUT_OF_BOUNDS:new J.ERR_OUT_OF_RANGE(e||"offset",`>= ${e?1:0} and <= ${t}`,r)}var Be=/[^+/0-9A-Za-z-_]/g;function me(r){if(r=r.split("=")[0],r=r.trim().replace(Be,""),r.length<2)return"";for(;r.length%4!==0;)r=r+"=";return r}function ht(r,t){t=t||1/0;let e,n=r.length,i=null,o=[];for(let u=0;u<n;++u){if(e=r.charCodeAt(u),e>55295&&e<57344){if(!i){if(e>56319){(t-=3)>-1&&o.push(239,191,189);continue}else if(u+1===n){(t-=3)>-1&&o.push(239,191,189);continue}i=e;continue}if(e<56320){(t-=3)>-1&&o.push(239,191,189),i=e;continue}e=(i-55296<<10|e-56320)+65536}else i&&(t-=3)>-1&&o.push(239,191,189);if(i=null,e<128){if((t-=1)<0)break;o.push(e)}else if(e<2048){if((t-=2)<0)break;o.push(e>>6|192,e&63|128)}else if(e<65536){if((t-=3)<0)break;o.push(e>>12|224,e>>6&63|128,e&63|128)}else if(e<1114112){if((t-=4)<0)break;o.push(e>>18|240,e>>12&63|128,e>>6&63|128,e&63|128)}else throw new Error("Invalid code point")}return o}function Ie(r){let t=[];for(let e=0;e<r.length;++e)t.push(r.charCodeAt(e)&255);return t}function Fe(r,t){let e,n,i,o=[];for(let u=0;u<r.length&&!((t-=2)<0);++u)e=r.charCodeAt(u),n=e>>8,i=e%256,o.push(i),o.push(n);return o}function Lt(r){return et.toByteArray(me(r))}function nt(r,t,e,n){let i;for(i=0;i<n&&!(i+e>=t.length||i>=r.length);++i)t[i+e]=r[i];return i}function v(r,t){return r instanceof t||r!=null&&r.constructor!=null&&r.constructor.name!=null&&r.constructor.name===t.name}function pt(r){return r!==r}var Ae=function(){let r="0123456789abcdef",t=new Array(256);for(let e=0;e<16;++e){let n=e*16;for(let i=0;i<16;++i)t[n+i]=r[e]+r[i]}return t}();function V(r){return typeof BigInt=="undefined"?be:r}function be(){throw new Error("BigInt not supported")}globalThis.Buffer=f,globalThis.SlowBuffer=Et;var De={Buffer:f,SlowBuffer:Et,INSPECT_MAX_BYTES:dt,kMaxLength:rt};var kt;(function(r){var t={};try{t.EventTarget=new Ot().constructor}catch{(function(n,i){var o=n.create,u=n.defineProperty,l=s.prototype;y(l,"addEventListener",function(a,b,m){for(var C=i.get(this),L=C[a]||(C[a]=[]),h=0,x=L.length;h<x;h++)if(L[h].listener===b)return;L.push({target:this,listener:b,options:m})}),y(l,"dispatchEvent",function(a){var b=i.get(this),m=b[a.type];return m&&(y(a,"target",this),y(a,"currentTarget",this),m.slice(0).some(p,a),delete a.currentTarget,delete a.target),!0}),y(l,"removeEventListener",function(a,b){for(var m=i.get(this),C=m[a]||(m[a]=[]),L=0,h=C.length;L<h;L++)if(C[L].listener===b){C.splice(L,1);return}}),t.EventTarget=s;function s(){"use strict";i.set(this,o(null))}function y(a,b,m){u(a,b,{configurable:!0,writable:!0,value:m})}function p(a){var b=a.options;return b&&b.once&&a.target.removeEventListener(this.type,a.listener),typeof a.listener=="function"?a.listener.call(a.target,this):a.listener.handleEvent(this),this._stopImmediatePropagationFlag}})(Object,new WeakMap)}kt=t.EventTarget,r.EventTarget=t.EventTarget})(globalThis);var Ot=kt,Me=Ot;var{apply:Te,construct:Ue}=Reflect;Function.prototype.once||Object.defineProperty(Function.prototype,"once",{writable:!0,configurable:!0,value(){let r=this,t=!0,e;return function n(){return t&&(t=!1,e=this instanceof n?Ue(r,arguments):Te(r,this,arguments)),e}}});var We=Wt(Pt());})();
// Elide JS Polyfills. Copyright (c) 2022, Sam Gammon and the Elide Project Authors. All rights reserved.
// Components of this software are licensed separately. See https://github.com/elide-dev/v3 for more.
// Built: Jan 9th, 2023, 20:30:00 PST.