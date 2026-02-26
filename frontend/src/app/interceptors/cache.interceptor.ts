import { HttpInterceptorFn, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, shareReplay, finalize } from 'rxjs/operators';

const TTL = 30 * 60 * 1000; // Time To Life - 30 min
const cacheMap = new Map<string, { response: any; expiry: number }>();
const inFlight = new Map<string, Observable<any>>();

export const cacheInterceptor: HttpInterceptorFn = (req, next) => {
  // Only works with GET Methods
  if (req.method !== 'GET') {
    return next(req);
  }

  // Attributes to verify whether or not the request is cached
  const token = req.headers.get('Authorization') ?? '';
  const cache_key = req.urlWithParams + '|' + token;

  const now = Date.now();

  // Check if the request data is already cached in memory
  const cachedInMemory = cacheMap.get(cache_key);
  if (cachedInMemory && cachedInMemory.expiry > now) {
    console.log('🔹 Cache hit (memory):', cache_key);
    return of(new HttpResponse({ body: cachedInMemory.response, status: 200 }));
  }

  // Check if the request data is already cached in LocalStorage.
  const cachedInLocalStorage = localStorage.getItem(cache_key);
  if (cachedInLocalStorage) {
    const cached = JSON.parse(cachedInLocalStorage) as { response: any; expiry: number };
    if (cached.expiry > now) {
      console.log('🔹 Cache hit (localStorage):', cache_key);
      cacheMap.set(cache_key, {response: cached.response, expiry: cached.expiry}); // Save request data in memory
      return of(new HttpResponse({ body: cached.response, status: 200 })); // Return an exact copy of the request
    } else {
      // Clean expired data
      localStorage.removeItem(cache_key);
    }
  }

  // Avoid duplicate requests
  if (inFlight.has(cache_key)) return inFlight.get(cache_key)!;

  // If the request data is not cached, we make the request to the backend.
  console.log('⚡ Cache miss, fetching from backend:', cache_key);
  const request = next(req).pipe(
    tap(data => {
      if (data instanceof HttpResponse) {
        console.log('✅ Response cached (localStorage):', cache_key);
        // Save in memory
        cacheMap.set(cache_key, { response: data.body, expiry: now + TTL });

        // Save in LocalStorage
        localStorage.setItem(cache_key, JSON.stringify({
          response: data.body,
          expiry: now + TTL
        }));
      }
    }),
    finalize(() => inFlight.delete(cache_key)), // Delete the request in memory of inFlight 
    shareReplay(1) // Unify the various requests into a single one
  );

  inFlight.set(cache_key, request);
  return request;
};