/**
 * question1 see Performance
 *
 * question2:根据前面描述的重载解析规则，能否重写默认方法中的equals 或hashCode 方法？
 * anseron2:yes
 * author:No - they are defined on java.lang.Object, and 'class always wins.'
 *
 * question3:
 * 3. 例4-26 所示的Artists 类表示了一组艺术家，重构该类，使得getArtist 方法返回一
 个Optional<Artist> 对象。如果索引在有效范围内，返回对应的元素，否则返回一个空
 Optional 对象。此外，还需重构getArtistName 方法，保持相同的行为。
 *
 * Created by Administrator on 2017-02-20.
 *
 * @author shitongtong
 */
package com.stt.java.base.java8.lambdas.book.examples.chapter4;