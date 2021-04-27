/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.sqlserver.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.dialect.sqlserver.ast.SQLServerObjectImpl;
import com.alibaba.druid.sql.dialect.sqlserver.visitor.SQLServerASTVisitor;

import java.util.Collections;
import java.util.List;

public class SQLServerCollateExpr extends SQLServerObjectImpl implements SQLServerExpr {

    protected SQLExpr expr;

    private  String collate;

    public SQLServerCollateExpr() {}

    public SQLServerCollateExpr(SQLExpr expr, String collate) {
        this.expr = expr;
        this.collate = collate;
    }

    @Override
    public List<SQLObject> getChildren() {
        return Collections.singletonList(expr);
    }

    @Override
    public void accept0(SQLServerASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, expr);
        }
        visitor.endVisit(this);
    }

    @Override
    public SQLServerCollateExpr clone() {
        SQLServerCollateExpr collateExpr = new SQLServerCollateExpr();
        collateExpr.setExpr(this.expr);
        collateExpr.setCollate(this.collate);
        return collateExpr;
    }

    public SQLExpr getExpr() {
        return expr;
    }

    public void setExpr(SQLExpr expr) {
        this.expr = expr;
    }

    public String getCollate() {
        return collate;
    }

    public void setCollate(String collate) {
        this.collate = collate;
    }
}
